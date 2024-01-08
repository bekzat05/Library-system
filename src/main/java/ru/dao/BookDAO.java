package ru.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.models.Book;
import ru.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE bookId=?",
                        new Object[]{id},
                        new BookMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book (bookName, bookAuthor, yearOfPublication) VALUES (?, ?, ?)",
                book.getBookName(),
                book.getBookAuthor(),
                book.getYearOfPublication());
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE Book SET bookName=?, bookAuthor=?, yearOfPublication=? WHERE bookId=?",
                updatedBook.getBookName(),
                updatedBook.getBookAuthor(),
                updatedBook.getYearOfPublication(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE bookId=?", id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.personId=Person.personId WHERE Book.bookId = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET personId=NULL WHERE bookId=?", id);
    }

    public void assign(int id, Person selectedPerson){
        jdbcTemplate.update("UPDATE Book SET personId=? WHERE bookId=?", selectedPerson.getPersonId(), id);
    }
}
