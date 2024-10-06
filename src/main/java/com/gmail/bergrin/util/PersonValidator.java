package com.gmail.bergrin.util;

import java.util.Optional;

import com.gmail.bergrin.model.Person;
import com.gmail.bergrin.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

  private final PeopleService peopleService;

  @Autowired
  public PersonValidator(PeopleService peopleService) {
    this.peopleService = peopleService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return Person.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Person targetPerson = (Person) target;
    Optional<Person> personFromDB = peopleService.findByEmail(targetPerson.getEmail());
    if (peopleService.findByEmail(targetPerson.getEmail()).isPresent() && targetPerson.getId() != personFromDB.get().getId()) {
      errors.rejectValue("email", "100", "This email is already exists");
    }
  }
}
