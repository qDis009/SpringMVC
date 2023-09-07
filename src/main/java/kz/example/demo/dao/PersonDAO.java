package kz.example.demo.dao;

import kz.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Person> index() {
        return jdbcTemplate.query("select *from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(int id) {
        return jdbcTemplate.query("select *from person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(fullName,age,email,address)", new Object[]{person.getFullName(), person.getAge(), person.getEmail(), person.getAddress()});
    }

    public void update(int id, Person updatedperson) {
        jdbcTemplate.update("update person set fullName=?,age=?,email=?,address=? where id=?", new Object[]{updatedperson.getFullName(), updatedperson.getAge(), updatedperson.getEmail(), updatedperson.getAddress(), id});
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id=?", new Object[]{id});
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("select *from person where fullName=?", new Object[]{fullName}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
    public Optional<Person> getPersonByEmail(String email){
        return jdbcTemplate.query("select *from person where email=",new Object[]{email},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
}
