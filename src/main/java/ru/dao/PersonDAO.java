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
public class PersonDAO {

    private final SessionFactory sessionFactory;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory, JdbcTemplate jdbcTemplate) {
        this.sessionFactory = sessionFactory;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional
    public void save(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class, id);
        personToBeUpdated.setPersonName(updatedPerson.getPersonName());
        personToBeUpdated.setYearOfBirth(updatedPerson.getYearOfBirth());
    }

    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

    public Optional<Person> getPersonByName(String personName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE personName=?", new Object[]{personName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE personId=?", new Object[]{id},
                new BookMapper());
    }
}
