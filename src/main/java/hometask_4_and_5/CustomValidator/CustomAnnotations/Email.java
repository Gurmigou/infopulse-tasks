package hometask_4_and_5.CustomValidator.CustomAnnotations;

import hometask_4_and_5.CustomValidator.CustomValidators.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {
    String message() default "Email is incorrect";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
