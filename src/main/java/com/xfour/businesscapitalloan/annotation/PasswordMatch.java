package com.xfour.businesscapitalloan.annotation;

import com.xfour.businesscapitalloan.utils.PasswordMatchValidator;

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