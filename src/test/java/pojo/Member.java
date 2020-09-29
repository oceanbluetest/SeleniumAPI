package pojo;

import com.github.javafaker.Faker;

import java.util.Random;

public class Member {
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String role;
    String password;

    public Member(){
        Faker faker = new Faker();
        String[] roles = {"Student", "Mentor", "Instructor"};

        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        phoneNumber = faker.phoneNumber().cellPhone();
        email = faker.internet().emailAddress();
        role = roles[new Random().nextInt(roles.length)];
        password = firstName + "." + lastName + "$";
    }

    public Member(String firstName, String lastName, String phoneNumber, String email, String role, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member \n{" +
                "\t firstName='" + firstName + '\'' +
                "\n\t lastName='" + lastName + '\'' +
                "\n\t phoneNumber='" + phoneNumber + '\'' +
                "\n\t email='" + email + '\'' +
                "\n\t role='" + role + '\'' +
                "\n\t password='" + password + '\'' +
                "\n\t}\"";
    }
}
