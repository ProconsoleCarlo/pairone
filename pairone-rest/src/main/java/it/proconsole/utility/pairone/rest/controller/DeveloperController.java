package it.proconsole.utility.pairone.rest.controller;

import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/developer")
public class DeveloperController {
  private final DeveloperRepository developerRepository;

  public DeveloperController(DeveloperRepository datastoreDeveloperRepository) {
    this.developerRepository = datastoreDeveloperRepository;
  }

  @GetMapping
  public List<Developer> getDevelopers() {
    return developerRepository.findAll();
  }

  @PostMapping
  public Developer saveDeveloper(@RequestBody Developer developer) {
    return developerRepository.save(developer);
  }
}