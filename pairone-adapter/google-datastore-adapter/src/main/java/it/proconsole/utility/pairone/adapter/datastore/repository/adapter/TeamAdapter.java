package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.adapter.datastore.model.TeamEntity;
import it.proconsole.utility.pairone.core.model.Team;

import java.util.List;
import java.util.Optional;

public class TeamAdapter {
  private final DeveloperAdapter developerAdapter;

  public TeamAdapter(DeveloperAdapter developerAdapter) {
    this.developerAdapter = developerAdapter;
  }

  public TeamEntity fromDomain(Team team) {
    var entity = new TeamEntity();
    Optional.ofNullable(team.id()).ifPresent(entity::setId);
    entity.setName(team.name());
    return entity;
  }

  public Team toDomain(TeamEntity team, List<DeveloperEntity> developerEntities) {
    return new Team(team.getId(), team.getName(), developerAdapter.toDomain(developerEntities));
  }
}
