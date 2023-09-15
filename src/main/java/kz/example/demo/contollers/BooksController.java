package kz.example.demo.contollers;

import kz.example.demo.dao.BookDAO;
import kz.example.demo.dao.PersonDAO;
import kz.example.demo.models.Book;
import kz.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    @Autowired
    BookDAO bookDAO;
    @Autowired
    PersonDAO personDAO;
    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/main";
    }
    @GetMapping("/{id}")
    public String details(@PathVariable("id") int id,
                          Model model,
                          @ModelAttribute("person") Person person){
        model.addAttribute("book",bookDAO.details(id));
        Optional<Person> bookOwner=bookDAO.getBookOwner(id);
        if(bookOwner.isPresent()){
            model.addAttribute("owner",bookOwner.get());
        }else{
            model.addAttribute("people",personDAO.index());
        }
        return "books/details";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,Model model){
        model.addAttribute("book",bookDAO.details(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.update(id,book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/"+id;
    }
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id,@ModelAttribute("person") Person selectedPerson){
        bookDAO.assign(id,selectedPerson);
        return "redirect:/books/"+id;
    }
}
