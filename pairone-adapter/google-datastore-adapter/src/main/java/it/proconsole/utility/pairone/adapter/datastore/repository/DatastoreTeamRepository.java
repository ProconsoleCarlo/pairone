package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.TeamAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.TeamEntityRepository;
import it.proconsole.utility.pairone.core.model.Team;
import it.proconsole.utility.pairone.core.repository.TeamRepository;

import java.util.List;

public class DatastoreTeamRepository implements TeamRepository {
  private final TeamEntityRepository teamEntityRepository;
  private final TeamAdapter teamAdapter;

  public DatastoreTeamRepository(
          TeamEntityRepository teamEntityRepository,
          TeamAdapter teamAdapter
  ) {
    this.teamEntityRepository = teamEntityRepository;
    this.teamAdapter = teamAdapter;
  }

  @Override
  public List<Team> findAll() {
    return teamAdapter.toDomain(teamEntityRepository.findAll());
  }

  @Override
  public List<Team> saveAll(List<Team> teams) {
    var entities = teamAdapter.fromDomain(teams);
    var savedEntities = teamEntityRepository.saveAll(entities);
    return teamAdapter.toDomain(savedEntities);
  }
}
