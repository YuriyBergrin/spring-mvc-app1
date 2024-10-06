package com.gmail.bergrin.repositories;

import java.util.List;

import com.gmail.bergrin.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

  List<Person> findByEmail(String email);
}
