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
  private final DeveloperEntityRepository developerEntityRepository;
  private final TeamEntityRepository teamEntityRepository;
  private final DeveloperAdapter developerAdapter;

  public DatastoreDeveloperRepository(
          DeveloperEntityRepository developerEntityRepository,
          TeamEntityRepository teamEntityRepository,
          DeveloperAdapter developerAdapter
  ) {
    this.developerEntityRepository = developerEntityRepository;
    this.teamEntityRepository = teamEntityRepository;
    this.developerAdapter = developerAdapter;
  }

  @Override
  public List<Developer> findAll() {
    return developerAdapter.toDomain(developerEntityRepository.findAll());
  }

  @Override
  public List<Developer> findByTeamId(Long teamId) {
    return developerAdapter.toDomain(developerEntityRepository.findByTeamId(teamId));
  }

  @Override
  public Developer save(Developer developer) {
    if (notExistsTeamWith(developer.teamId())) {
      throw EntityNotFoundException.forTeam(developer.teamId());
    }
    var entity = developerAdapter.fromDomain(developer);
    var savedEntity = developerEntityRepository.save(entity);
    return developerAdapter.toDomain(savedEntity);
  }

  private boolean notExistsTeamWith(@Nullable Long teamId) {
    return teamId != null && teamEntityRepository.findById(teamId).isEmpty();
  }
}
