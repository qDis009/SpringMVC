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
    public List<Person> index(){
        return jdbcTemplate.query("select *from person",new BeanPropertyRowMapper<>(Person.class));
    }
    public Optional<Person> show(int id){
        return jdbcTemplate.query("select *from person where id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
}
