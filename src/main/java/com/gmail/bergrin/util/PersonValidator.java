package com.gmail.bergrin.util;

import java.util.Optional;

import com.gmail.bergrin.dao.PersonDao;
import com.gmail.bergrin.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

  private final PersonDao personDao;

  @Autowired
  public PersonValidator(PersonDao personDao) {
    this.personDao = personDao;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Person.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Person targetPerson = (Person) target;
    Optional<Person> personFromDB = personDao.show(targetPerson.getEmail());
    if (personDao.show(targetPerson.getEmail()).isPresent() && targetPerson.getId() != personFromDB.get().getId()) {
      errors.rejectValue("email", "100", "This email is already exists");
    }
  }
}
