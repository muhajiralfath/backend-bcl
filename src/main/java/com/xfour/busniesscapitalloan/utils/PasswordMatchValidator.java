package com.xfour.busniesscapitalloan.utils;

import com.xfour.busniesscapitalloan.annotation.PasswordMatch;
import com.xfour.busniesscapitalloan.model.request.ChangePasswordRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof ChangePasswordRequest) {
            ChangePasswordRequest request = (ChangePasswordRequest) value;
            boolean isValid = request.getNewPassword() != null && request.getNewPassword().equals(request.getConfirmPassword());

            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addPropertyNode("confirmPassword")
                        .addConstraintViolation();
            }

            return isValid;
        }
        return true;
    }
}
