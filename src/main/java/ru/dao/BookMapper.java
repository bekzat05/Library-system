package ru.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setBookId(rs.getInt("bookId"));
        book.setPersonId(rs.getInt("personId"));
        book.setBookName(rs.getString("bookName"));
        book.setBookAuthor(rs.getString("bookAuthor"));
        book.setYearOfPublication(rs.getInt("yearOfPublication"));

        return book;
    }
}
