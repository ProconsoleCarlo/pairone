package it.proconsole.utility.pairone.rest.controller;

import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

  private final DeveloperRepository developerRepository;

  public HelloController(DeveloperRepository datastoreDeveloperRepository) {
    this.developerRepository = datastoreDeveloperRepository;
  }

  @GetMapping("/findAll")
  public List<Developer> findAll() {
    return developerRepository.findAll();
  }

  @GetMapping("/save")
  public List<Developer> save() {
    return developerRepository.saveAll(List.of(new Developer(1L, "pippo")));
  }
}