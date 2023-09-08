package kz.example.demo.dao;

import kz.example.demo.models.Book;
import kz.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Book> index(){
        return jdbcTemplate.query("select *from book",new BeanPropertyRowMapper<>(Book.class));
    }
    public Optional<Book> details(int id){
        return jdbcTemplate.query("select *from book where id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }
    public void save(Book book){
        jdbcTemplate.update("insert into book(title,author,year) values (?,?,?)",new Object[]{book.getTitle(),book.getAuthor(),book.getYear()});
    }
    public void update(int id,Book updatedbook){
        jdbcTemplate.update("update book set title=?,author=?,year=? where id=?",new Object[]{updatedbook.getTitle(),updatedbook.getAuthor(),updatedbook.getYear(),id});
    }
    public void delete(int id) {
        jdbcTemplate.update("delete from book where id=?", new Object[]{id});
    }
    //Находим владельца книги по его id
    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("select person.* from book join person on book book.person_id=person.id where book.id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
    //Этот медот освождает книгу когда человек возвращает её
    public void release(int id){
        jdbcTemplate.update("update book set person_id=null where id=?",new Object[]{id});
    }
    //Назначает книгу человеку когда он берет её из библиотеки
    public void assign(int id, Person selectedPerson){
        jdbcTemplate.update("update book set person_id=? where id=?",new Object[]{selectedPerson.getId(),id});
    }
}
