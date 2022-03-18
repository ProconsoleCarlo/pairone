package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.TeamEntity;
import it.proconsole.utility.pairone.core.model.Team;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeamAdapter {
  private final DeveloperAdapter developerAdapter;

  public TeamAdapter(DeveloperAdapter developerAdapter) {
    this.developerAdapter = developerAdapter;
  }

  public List<TeamEntity> fromDomain(List<Team> teams) {
    return teams.stream().map(this::fromDomain).collect(Collectors.toList());
  }

  public List<Team> toDomain(List<TeamEntity> teams) {
    return teams.stream().map(this::toDomain).collect(Collectors.toList());
  }

  private TeamEntity fromDomain(Team team) {
    var entity = new TeamEntity();
    Optional.ofNullable(team.id()).ifPresent(entity::setId);
    entity.setName(team.name());
    return entity;
  }

  private Team toDomain(TeamEntity team) {
    return new Team(team.getId(), team.getName(), Collections.emptyList());
  }
}
