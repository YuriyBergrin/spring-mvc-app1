package com.gmail.bergrin.controllers;

import com.gmail.bergrin.dao.PersonDao;
import com.gmail.bergrin.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  private final PersonDao personDao;

  @Autowired
  public AdminController(PersonDao personDao) {
    this.personDao = personDao;
  }

  @GetMapping
  public String adminPage(Model model, @ModelAttribute("person") Person person) {
    model.addAttribute("people", personDao.index());
    return "adminPage";
  }

  @PatchMapping("/add")
  public String makeAdmin(@ModelAttribute("person") Person person) {
    System.out.println("Admin is: %d".formatted(person.getId()));
    return "redirect:/people";
  }
}
