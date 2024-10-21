package API.helpers;

import com.github.javafaker.Faker;

public class Person {

    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String location;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private static final Faker faker = new Faker();


    public Person() {
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.dob = faker.date().birthday().toString();
        this.email = faker.internet().emailAddress();
        this.location = faker.address().city();
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

}
