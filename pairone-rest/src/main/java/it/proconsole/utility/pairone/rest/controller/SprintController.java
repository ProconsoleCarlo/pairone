package it.proconsole.utility.pairone.rest.controller;

import it.proconsole.utility.pairone.core.logic.PairsGenerator;
import it.proconsole.utility.pairone.core.model.Sprint;
import it.proconsole.utility.pairone.core.repository.SprintRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SprintController {
  private final SprintRepository sprintRepository;
  private final PairsGenerator pairsGenerator;

  public SprintController(
          SprintRepository datastoreSprintRepository,
          PairsGenerator pairsGenerator
  ) {
    this.sprintRepository = datastoreSprintRepository;
    this.pairsGenerator = pairsGenerator;
  }

  @GetMapping("/team/{teamId}/sprint")
  public List<Sprint> getSprints(@PathVariable Long teamId) {
    return sprintRepository.findByTeamId(teamId);
  }

  @PostMapping("/team/{teamId}/sprint/create")
  public List<Sprint> createSprints(@PathVariable Long teamId) {
    var sprints = pairsGenerator.generateFor(teamId);
    return sprintRepository.saveAll(teamId, sprints);
  }

  @PostMapping("/team/{teamId}/sprint")
  public List<Sprint> saveSprints(@PathVariable Long teamId, @RequestBody List<Sprint> sprints) {
    return sprintRepository.saveAll(teamId, sprints);
  }
}