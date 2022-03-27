package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.DeveloperAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.DeveloperEntityRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.TeamEntityRepository;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;

import java.util.List;
import java.util.Optional;

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
  public Developer save(Developer developer) {
    if (Optional.ofNullable(developer.teamId())
            .map(teamEntityRepository::findById)
            .isEmpty()) {
      throw new RuntimeException();
    }
    var entity = developerAdapter.fromDomain(developer);
    var savedEntity = developerEntityRepository.save(entity);
    return developerAdapter.toDomain(savedEntity);
  }

  @Override
  public List<Developer> saveAll(List<Developer> developers) {
    var entities = developerAdapter.fromDomain(developers);
    var savedEntities = developerEntityRepository.saveAll(entities);
    return developerAdapter.toDomain(savedEntities);
  }
}
