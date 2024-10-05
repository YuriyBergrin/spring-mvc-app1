package com.gmail.bergrin.dao;

import java.util.List;
import java.util.Optional;

import com.gmail.bergrin.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonDao {

  private final SessionFactory sessionFactory;

  @Autowired
  public PersonDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Transactional(readOnly = true)
  public List<Person> index() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from Person p", Person.class).getResultList();
  }

  @Transactional(readOnly = true)
  public Person show(int id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Person.class, id);
  }

  @Transactional(readOnly = true)
  public Optional<Person> show(String email) {
    Session session = sessionFactory.getCurrentSession();
    String hqlQuery = "from Person p where p.email = :email";
    Query<Person> query = session.createQuery(hqlQuery, Person.class);
    query.setParameter("email", email);
    return query.uniqueResultOptional();
  }

  @Transactional
  public void save(Person person) {
    Session session = sessionFactory.getCurrentSession();
    session.persist(person);
  }

  @Transactional
  public void update(int id, Person updatedPerson) {
    Session session = sessionFactory.getCurrentSession();
    Person personInDb = session.get(Person.class, id);
    personInDb.setName(updatedPerson.getName());
    personInDb.setAge(updatedPerson.getAge());
    personInDb.setEmail(updatedPerson.getEmail());
    personInDb.setAddress(updatedPerson.getAddress());
  }

  @Transactional
  public void delete(int id) {
    Session session = sessionFactory.getCurrentSession();
    Person personInDb = session.get(Person.class, id);
    session.remove(personInDb);
  }
}
