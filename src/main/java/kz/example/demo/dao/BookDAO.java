package kz.example.demo.dao;

import kz.example.demo.models.Book;
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
    public void delete(int id){
        jdbcTemplate.update("delete from book where id=?",new Object[]{id});
    }

}
