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
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE personid=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
        jdbcTemplate.update("INSERT INTO Person (personName, yearOfBirth) VALUES (?, ?)",
                person.getPersonName(),
                person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE Person SET personName=?, yearOfBirth=? WHERE personid=?",
                updatedPerson.getPersonName(),
                updatedPerson.getYearOfBirth(),
                id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE personid=?", id);
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
