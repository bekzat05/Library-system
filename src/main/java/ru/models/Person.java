package ru.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int personId;

    @NotEmpty(message = "The name cannot be empty!")
    @Size(min = 2, max = 100, message = "The length of the name must be between 2 and 100!")
    private String personName;


    private int yearOfBirth;

    public Person() {
    }

    public Person(int personId, String personName, int yearOfBirth) {
        this.personId = personId;
        this.personName = personName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
