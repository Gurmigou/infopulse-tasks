package ValidationTest;

import hometask_4_and_5.CustomValidator.User;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class TestMain {

    @Test
    public void shouldPassValidation() {
        User user = new User(18, true, "egor555x6@gmail.com", "+380661157385");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        validate.forEach(c -> System.out.println("***** " + c.getMessage()));
    }
}
