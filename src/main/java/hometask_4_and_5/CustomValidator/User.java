package hometask_4_and_5.CustomValidator;

import hometask_4_and_5.CustomValidator.CustomAnnotations.Email;
import hometask_4_and_5.CustomValidator.CustomAnnotations.Phone;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;

public class User {
    @Min(18)
    private int age;
    @AssertTrue
    private boolean isStudent;

    @Email
    private String email;

    @Phone
    private String phoneNumber;

    public User(int age, boolean isStudent, String email, String phoneNumber) {
        this.age = age;
        this.isStudent = isStudent;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
