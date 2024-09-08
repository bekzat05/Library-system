package ru.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "bookid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @ManyToOne
    @JoinColumn(name = "personid", referencedColumnName = "personid")
    private Person personId;

    @NotEmpty(message = "The title of the book cannot be empty!")
    @Size(min = 2, max = 100, message = "The length of the title must be between 2 and 100!")
    @Column(name = "bookname")
    private String bookName;

    @NotEmpty(message = "The author cannot be blank!")
    @Size(min = 2, max = 100, message = "The length of the author's name must be between 2 and 100!")
    @Column(name = "bookauthor")
    private String bookAuthor;

    @Min(value = 1500, message = "The year of the publication must after 1500")
    @Column(name = "yearofpublication")
    private int yearOfPublication;

    public Book() {
    }

    public Book(Person personId, String bookName, String bookAuthor, int yearOfPublication) {
        this.personId = personId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.yearOfPublication = yearOfPublication;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }
}
