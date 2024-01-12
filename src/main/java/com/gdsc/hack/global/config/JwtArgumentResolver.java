package com.gdsc.hack.global.config;

import com.gdsc.hack.global.JwtResolver;
import com.gdsc.hack.service.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JwtResolver.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        // 헤더 값 체크
        if (httpServletRequest != null) {

            String token = httpServletRequest.getHeader("Authorization");

            log.info("resolver toke {}",token);

            if (token != null && !token.trim().equals("")) {
                // 토큰 있을 경우 검증
                if (tokenProvider.validateToken(token)) {
                    // 검증 후 AuthenticationUser 리턴
                    return tokenProvider.getAuthUser(token);
                }
            }

            // 토큰 값이 없으면 에러
            throw new IllegalAccessException("토큰이 존재하지 않습니다.");
        }

        return null;
    }
}
