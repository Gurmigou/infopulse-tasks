package hometask_4_and_5.CustomValidator.CustomAnnotations;

import hometask_4_and_5.CustomValidator.CustomValidators.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {
    String message() default "Phone number is incorrect";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
