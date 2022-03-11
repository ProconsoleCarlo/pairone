package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.DeveloperAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.DeveloperEntityRepository;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatastoreDeveloperRepositoryTest {
  @Mock
  private DeveloperEntityRepository developerEntityRepository;
  @Mock
  private DeveloperAdapter developerAdapter;

  private DeveloperRepository repository;

  @BeforeEach
  void setUp() {
    repository = new DatastoreDeveloperRepository(developerEntityRepository, developerAdapter);
  }

  @Test
  void findAll() {
    var entities = List.of(
            new DeveloperEntity(1L, "Dev1"),
            new DeveloperEntity(2L, "Dev2")
    );
    var domain = List.of(
            new Developer(1L, "Dev1"),
            new Developer(2L, "Dev2")
    );
    when(developerEntityRepository.findAll()).thenReturn(entities);
    when(developerAdapter.toDomain(entities)).thenReturn(domain);

    assertEquals(domain, repository.findAll());
  }

  @Test
  void saveAll() {
    var domainToSave = List.of(
            new Developer("Dev1"),
            new Developer(9L, "Dev2")
    );
    var entitiesToSave = List.of(
            new DeveloperEntity("Dev1"),
            new DeveloperEntity(9L, "Dev2")
    );
    var entitiesSaved = List.of(
            new DeveloperEntity(1L, "Dev1"),
            new DeveloperEntity(9L, "Dev2")
    );
    var domainSaved = List.of(
            new Developer(1L, "Dev1"),
            new Developer(9L, "Dev2")
    );
    when(developerAdapter.fromDomain(domainToSave)).thenReturn(entitiesToSave);
    when(developerEntityRepository.saveAll(entitiesToSave)).thenReturn(entitiesSaved);
    when(developerAdapter.toDomain(entitiesSaved)).thenReturn(domainSaved);

    assertEquals(domainSaved, repository.saveAll(domainToSave));
  }
}