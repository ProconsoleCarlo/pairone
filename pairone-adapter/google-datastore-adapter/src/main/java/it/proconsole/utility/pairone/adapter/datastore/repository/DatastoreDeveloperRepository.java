package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.DeveloperAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.datastore.DeveloperEntityRepository;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;

import java.util.List;

public class DatastoreDeveloperRepository implements DeveloperRepository {
  private final DeveloperEntityRepository developerEntityRepository;
  private final DeveloperAdapter developerAdapter;

  public DatastoreDeveloperRepository(
          DeveloperEntityRepository developerEntityRepository,
          DeveloperAdapter developerAdapter
  ) {
    this.developerEntityRepository = developerEntityRepository;
    this.developerAdapter = developerAdapter;
  }

  @Override
  public List<Developer> findAll() {
    return developerAdapter.toDomain(developerEntityRepository.findAll());
  }

  @Override
  public List<Developer> saveAll(List<Developer> developers) {
    var entities = developerAdapter.fromDomain(developers);
    var savedEntities = developerEntityRepository.saveAll(entities);
    return developerAdapter.toDomain(savedEntities);
  }
}
