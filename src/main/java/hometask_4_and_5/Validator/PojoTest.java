package hometask_4_and_5.Validator;

import hometask_4_and_5.Validator.Annotations.*;

import java.util.Set;

public class PojoTest {

    public static void main(String[] args) {
        var pojoTest = new PojoTest(-5, 0, "abcd", null, false);

        var validator = new Validator();
        Set<String> validate = validator.validate(pojoTest);

        // prints all validation errors
        validate.forEach(System.out::println);
    }

    @Min(value = 0)
    @Max(value = 5)
    private int value1;

    @Min(value = 10)
    private int value2;

    @Size(size = 3)
    private String name;

    @NotNull
    private Set<String> things;

    @AssertTrue
    private boolean homeWorkDone;

    public PojoTest(int value1, int value2, String name, Set<String> things, boolean homeWorkDone) {
        this.value1 = value1;
        this.value2 = value2;
        this.name = name;
        this.things = things;
        this.homeWorkDone = homeWorkDone;
    }
}
