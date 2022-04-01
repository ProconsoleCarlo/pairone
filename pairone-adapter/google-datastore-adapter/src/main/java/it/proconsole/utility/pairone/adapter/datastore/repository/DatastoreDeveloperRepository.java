package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.exception.EntityNotFoundException;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.DeveloperAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.DeveloperEntityRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.TeamEntityRepository;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import org.springframework.lang.Nullable;

import java.util.List;

public class DatastoreDeveloperRepository implements DeveloperRepository {
  private final DeveloperEntityRepository entityRepository;
  private final TeamEntityRepository teamEntityRepository;
  private final DeveloperAdapter developerAdapter;

  public DatastoreDeveloperRepository(
          DeveloperEntityRepository entityRepository,
          TeamEntityRepository teamEntityRepository,
          DeveloperAdapter developerAdapter
  ) {
    this.entityRepository = entityRepository;
    this.teamEntityRepository = teamEntityRepository;
    this.developerAdapter = developerAdapter;
  }

  @Override
  public List<Developer> findAll() {
    return developerAdapter.toDomain(entityRepository.findAll());
  }

  @Override
  public List<Developer> findAllById(List<Long> teamIds) {
    return developerAdapter.toDomain(entityRepository.findAllById(teamIds));
  }

  @Override
  public List<Developer> findByTeamId(Long teamId) {
    return developerAdapter.toDomain(entityRepository.findByTeamId(teamId));
  }

  @Override
  public Developer save(Developer developer) {
    if (notExistsTeamWith(developer.teamId())) {
      throw EntityNotFoundException.forTeam(developer.teamId());
    }
    var entity = developerAdapter.fromDomain(developer);
    var savedEntity = entityRepository.save(entity);
    return developerAdapter.toDomain(savedEntity);
  }

  private boolean notExistsTeamWith(@Nullable Long teamId) {
    return teamId != null && teamEntityRepository.findById(teamId).isEmpty();
  }
}
