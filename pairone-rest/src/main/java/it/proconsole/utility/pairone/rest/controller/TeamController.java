package it.proconsole.utility.pairone.rest.controller;

import it.proconsole.utility.pairone.core.model.Team;
import it.proconsole.utility.pairone.core.repository.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
  private final TeamRepository teamRepository;

  public TeamController(TeamRepository datastoreTeamRepository) {
    this.teamRepository = datastoreTeamRepository;
  }

  @GetMapping
  public List<Team> getTeams() {
    return teamRepository.findAll();
  }

  @PostMapping
  public List<Team> saveTeams(@RequestBody List<Team> teams) {
    return teamRepository.saveAll(teams);
  }
}