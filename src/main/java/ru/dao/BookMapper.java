package ru.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.RowMapper;
import ru.models.Book;
import ru.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookMapper implements RowMapper<Book> {


    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();


        Integer person_id = rs.getInt("personId");
        Person person = new Person();
        person.setPersonId(person_id);


        book.setBookId(rs.getInt("bookId"));
        book.setPersonId(person);
        book.setBookName(rs.getString("bookName"));
        book.setBookAuthor(rs.getString("bookAuthor"));
        book.setYearOfPublication(rs.getInt("yearOfPublication"));

        return book;
    }
}
