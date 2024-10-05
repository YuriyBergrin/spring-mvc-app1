package com.gmail.bergrin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "person")
public class Person {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "age")
  @Min(value = 0, message = "Age could  be >0")
  private int age;
  @Column(name = "name")
  @NotEmpty(message = "Name shouldn't be empty")
  @Size(min = 2, max = 30, message = "Name should consist of 2 and 30 characters")
  private String name;
  @Column(name = "email")
  @NotEmpty(message = "Email shouldn't be empty")
  @Email(message = "Email should be valid")
  private String email;
  @Column(name = "address")
  //Pattern: Country City zipcode (6 numbers)
  @Pattern(regexp = "^[A-Z][a-zA-Z\\- ]+, [A-Z][a-zA-Z\\- ]+, \\d{6}$", message = "Address format error. Pattern: Country City zipcode (6 numbers)")
  private String address;

  public Person() {
  }

  public Person(int id, int age, String name, String email, String address) {
    this.id = id;
    this.age = age;
    this.name = name;
    this.email = email;
    this.address = address;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
