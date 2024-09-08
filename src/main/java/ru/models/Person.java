package ru.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "personid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @NotEmpty(message = "The name cannot be empty!")
    @Size(min = 2, max = 100, message = "The length of the name must be between 2 and 100!")
    @Column(name = "personname")
    private String personName;

    @Min(value = 1900, message = "The year of birth cannot be less than 1900!")
    @Column(name = "yearofbirth")
    private int yearOfBirth;

    public Person() {
    }

    public Person(String personName, int yearOfBirth) {
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
