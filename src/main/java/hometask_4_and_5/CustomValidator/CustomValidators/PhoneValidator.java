package hometask_4_and_5.CustomValidator.CustomValidators;

import hometask_4_and_5.CustomValidator.CustomAnnotations.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    /**
     * Note that this regular expression tests only ukrainian phone numbers
     */
    private static final String phoneNumberRegex = "^\\+?3?8?(0[5-9][0-9]\\d{7})$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        return s != null && s.matches(phoneNumberRegex);
    }
}
