package hometask_4_and_5.Validator;

import hometask_4_and_5.Validator.Annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validator {
    private final Set<String> errors = new HashSet<>();

    private final Set<Class<?>> supportedAnnotations =
            Set.of(AssertTrue.class, Max.class, Min.class, NotNull.class, Size.class);

    public Validator() {
    }

    public Set<String> validate(Object o) {
        Field[] declaredFields = o.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();

            var annotationValidator = new AnnotationValidator(errors);

            Arrays.stream(annotations)
                  .filter(a -> supportedAnnotations.contains(a.annotationType()))
                  .forEach(a -> {
                      try {
                          annotationValidator.validateValues(a, field.getName(), field.get(o));
                      } catch (IllegalAccessException e) {
                          e.printStackTrace();
                      }
                  });
        }

        return errors;
    }
}
