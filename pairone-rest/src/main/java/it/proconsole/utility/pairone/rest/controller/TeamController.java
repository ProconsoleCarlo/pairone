package it.proconsole.utility.pairone.rest.controller;

import it.proconsole.utility.pairone.core.model.Team;
import it.proconsole.utility.pairone.core.repository.TeamRepository;
import it.proconsole.utility.pairone.rest.exception.TeamNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {
  private final TeamRepository teamRepository;

  public TeamController(TeamRepository datastoreTeamRepository) {
    this.teamRepository = datastoreTeamRepository;
  }

  @GetMapping("/{teamId}")
  public Team getTeam(@PathVariable Long teamId) {
    return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
  }

  @PostMapping
  public Team updateTeam(@RequestBody Team team) {
    return teamRepository.save(team);
  }

  @PutMapping
  public Team createTeam(@RequestBody Team team) {
    return teamRepository.save(team);
  }

  @ExceptionHandler(TeamNotFoundException.class)
  public ResponseEntity<String> notFound(TeamNotFoundException e) {
    return ResponseEntity.notFound().build();
  }
}