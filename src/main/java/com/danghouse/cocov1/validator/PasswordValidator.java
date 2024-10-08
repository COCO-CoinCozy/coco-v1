package com.danghouse.cocov1.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("❗비밀번호를 입력해주세요.")
                    .addConstraintViolation();
            return false;
        }

        if (password.length() < 4 || password.length() > 10) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("❗비밀번호는 4자 이상 10자 이하로 입력해주세요.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}