package kz.example.demo.contollers;

import kz.example.demo.dao.BookDAO;
import kz.example.demo.dao.PersonDAO;
import kz.example.demo.models.Person;
import kz.example.demo.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    PersonDAO personDAO;
    @Autowired
    PersonValidator personValidator;
    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",personDAO.index());
        return "people/main";
    }
    @GetMapping("/{id}")
    public String details(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDAO.details(id));
        model.addAttribute("books", personDAO.getBooksByPersonId(id));
        return "people/details";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors()){
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,Model model){
        model.addAttribute("person",personDAO.details(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "people/edit";
        }
        personDAO.update(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
