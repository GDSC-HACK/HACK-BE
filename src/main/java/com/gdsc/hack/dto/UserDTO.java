package com.gdsc.hack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    @Email(message = "이메일 입력은 필수 입니다.")
    @NotNull
    private String email;
    private String nickname;
    private String password;
//    private String refreshToken;
    private String accessToken;
}
