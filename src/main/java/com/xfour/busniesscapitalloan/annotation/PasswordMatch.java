package com.xfour.busniesscapitalloan.annotation;

import com.xfour.busniesscapitalloan.utils.PasswordMatchValidator;
import com.xfour.busniesscapitalloan.utils.ValidationUtil;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatch {

    String message() default "password dan confirm password harus sama";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}