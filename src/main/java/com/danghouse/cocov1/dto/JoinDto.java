package com.danghouse.cocov1.dto;

import com.danghouse.cocov1.validator.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class JoinDto {

    @NotBlank(message = "❗닉네임을 입력해주세요.")
    private String username;

    @NotBlank(message = "❗이메일 주소를 입력해주세요.")
    @Email(message = "❗올바른 이메일 주소를 입력해주세요.")
    private String email;

    @ValidPassword
    private String password;
}
