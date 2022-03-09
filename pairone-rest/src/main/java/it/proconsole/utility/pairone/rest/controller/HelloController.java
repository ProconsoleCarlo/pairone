package it.proconsole.utility.pairone.rest.controller;

import it.proconsole.utility.pairone.adapter.datastore.model.Person;
import it.proconsole.utility.pairone.adapter.datastore.repository.PersonRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class HelloController {

  private final PersonRepository personRepository;

  public HelloController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @GetMapping("/findAll")
  public List<Person> findAll() {
    return personRepository.findAll();
  }

  @GetMapping("/save")
  public void save() {
    personRepository.save(
            new Person(
                    new Random().nextLong(),
                    RandomStringUtils.random(10),
                    RandomStringUtils.random(8))
    );
  }

}