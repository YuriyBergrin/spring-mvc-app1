package com.gmail.bergrin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.gmail.bergrin.model.Person;
import com.gmail.bergrin.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

  private final PeopleRepository peopleRepository;

  @Autowired
  public PeopleService(PeopleRepository peopleRepository) {
    this.peopleRepository = peopleRepository;
  }

  public List<Person> findAll() {
    return peopleRepository.findAll();
  }

  public Person findById(int id) {
    Optional<Person> foundPerson =  peopleRepository.findById(id);
    return foundPerson.orElse(null);
  }

  @Transactional
  public void save(Person person) {
    person.setCreatedAt(LocalDateTime.now());
    peopleRepository.save(person);
  }

  @Transactional
  public void update(int id, Person updatedPerson) {
    updatedPerson.setId(id);
    updatedPerson.setCreatedAt(findById(id).getCreatedAt());
    peopleRepository.save(updatedPerson);
  }

  @Transactional
  public void delete(int id) {
    peopleRepository.deleteById(id);
  }

  @Transactional
  public Optional<Person> findByEmail(String email) {
    return peopleRepository.findByEmail(email).stream().findFirst();
  }
}
