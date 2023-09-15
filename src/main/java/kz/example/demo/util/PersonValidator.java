package kz.example.demo.util;

import kz.example.demo.dao.PersonDAO;
import kz.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    @Autowired
    PersonDAO personDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Person person=(Person) target;
        if(personDAO.getPersonByFullName(person.getFullName()).isPresent()){
            errors.rejectValue("fullName","","Человек с таким именем уже существует");
        }
        if(personDAO.getPersonByEmail(person.getEmail()).isPresent()){
            errors.rejectValue("email","","Данный email уже зарегистрирован в базе данных");
        }
    }
}
