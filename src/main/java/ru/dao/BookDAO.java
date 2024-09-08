package ru.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.models.Book;
import ru.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final SessionFactory sessionFactory;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(SessionFactory sessionFactory, JdbcTemplate jdbcTemplate) {
        this.sessionFactory = sessionFactory;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public List<Book> index(){
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Book show(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Transactional
    public void save(Book book){
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        Session session = sessionFactory.getCurrentSession();
        Book bookToBeUpdated = session.get(Book.class, id);
        bookToBeUpdated.setBookName(updatedBook.getBookName());
        bookToBeUpdated.setBookAuthor(updatedBook.getBookAuthor());
        bookToBeUpdated.setYearOfPublication(updatedBook.getYearOfPublication());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Book.class, id));
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.personId=Person.personId WHERE Book.bookId = ?"
                        , new Object[]{id}
                        , new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    @Transactional
    public void release(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("update Book b set b.personId = null where b.bookId = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public void assign(int id, Person selectedPerson){
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("update Book b set b.personId = :personId where b.bookId = :id").
                setParameter("personId", selectedPerson).
                setParameter("id", id)
                .executeUpdate();
    }
}
