package hometask_4_and_5.Validator;

import hometask_4_and_5.Validator.Annotations.*;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AnnotationValidator {
    private final Set<String> errors;

    public AnnotationValidator(Set<String> errors) {
        this.errors = errors;
    }

    private void checkCorrectness(Consumer<Object> consumer, Object o, Supplier<String> castError) {
        try {
            consumer.accept(o);
        } catch (ClassCastException e) {
            errors.add(castError.get());
        }
    }

    private void assertTrueValidate(AssertTrue annotation, String fieldName, Object fieldValue) {
        checkCorrectness((o) -> {
            boolean value = (boolean) o;
            if (!value) {
                errors.add("Value of field " + fieldName + " isn't true");
            }
        }, fieldValue, () -> "Field " + fieldName + " must be boolean type");
    }

    private void maxValidate(Max annotation, String fieldName, Object fieldValue) {
        checkCorrectness((o) -> {
            int value = (int) o;
            if (value > annotation.value())
                errors.add("Value of field " + fieldName + " is > than " + annotation.value());
        }, fieldValue, () -> "Field " + fieldName + " must be integer type");
    }

    private void minValidate(Min annotation, String fieldName, Object fieldValue) {
        checkCorrectness((o) -> {
            int value = (int) o;
            if (value < annotation.value())
                errors.add("Value of field " + fieldName + " is < " + annotation.value());
        }, fieldValue, () -> "Field " + fieldName + " must integer type");
    }

    private void notNullValidate(NotNull annotation, String fieldName, Object fieldValue) {
        checkCorrectness((o) -> {
            if (fieldValue == null)
                errors.add("Field " + fieldName + " is null");
        }, fieldValue, () -> "Field " + fieldName + " must be not null");
    }

    private void sizeValidate(Size annotation, String fieldName, Object fieldValue) {
        checkCorrectness((o) -> {
            String value = (String) fieldValue;
            if (value.length() != annotation.size())
                errors.add("Field " + fieldName + " size != " + annotation.size());
        }, fieldValue, () -> "Field " + fieldName + " must be string type");
    }

    public void validateValues(Annotation annotation, String fieldName, Object fieldValue) {
        if (annotation instanceof AssertTrue assertTrue)
            assertTrueValidate(assertTrue, fieldName, fieldValue);
        else if (annotation instanceof Max max)
            maxValidate(max, fieldName, fieldValue);
        else if (annotation instanceof Min min)
            minValidate(min, fieldName, fieldValue);
        else if (annotation instanceof NotNull notNull)
            notNullValidate(notNull, fieldName, fieldValue);
        else if (annotation instanceof Size size)
            sizeValidate(size, fieldName, fieldValue);
    }
}
