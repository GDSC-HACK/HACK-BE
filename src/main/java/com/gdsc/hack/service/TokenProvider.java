package com.gdsc.hack.service;

import com.gdsc.hack.domain.RefreshToken;
import com.gdsc.hack.domain.User;
import com.gdsc.hack.dto.AuthUser;
import com.gdsc.hack.dto.TokenDTO;
import com.gdsc.hack.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
@Component
public class TokenProvider {

    private final String SECRET_KEY;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenProvider(@Value("${secret.key}") String SECRET_KEY, RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.SECRET_KEY=SECRET_KEY;
    }

    /*
            JWT 토큰생성
            header(타입,알고리즘) / payload(내용) / signature(서명)
         */
    public String createAccessToken(String email){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .setSubject(email) // String 변경
                .setIssuer("Jwt login")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now()
                        .plus(1, ChronoUnit.DAYS)))
                .compact();
    }

    public boolean validateToken(String token) throws Exception{
        try {
            return !Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(parseBearer(token))
                    .getBody()
                    .getExpiration().before(new Date());

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalAccessException("유효하지않는 토큰");
        }
    }

    public TokenDTO createToken(User user){
        String accessToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setSubject(user.getEmail()) // String 변경
                .setIssuer("access")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now()
                        .plus(15, ChronoUnit.MINUTES)))
                .compact();
        //분리

        String refreshToken=Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setSubject(user.getEmail()) // String 변경
                .setIssuer("refresh")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now()
                        .plus(15, ChronoUnit.DAYS)))
                .compact();

        // DB에 리프레시토큰 저장
        RefreshToken newRefreshToken= RefreshToken.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        refreshTokenRepository.save(newRefreshToken);

        return new TokenDTO(accessToken,refreshToken);
    }


    public AuthUser getAuthUser(String token){
        Claims getClaims=Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(parseBearer(token))
                .getBody();


        return AuthUser.builder()
                .nickname(getClaims.getSubject())
                .build();
    }



    /*
    헤더 파싱
     */
    private String parseBearer(String authorization){
        return authorization.replace("Bearer","").trim();
    }
}
