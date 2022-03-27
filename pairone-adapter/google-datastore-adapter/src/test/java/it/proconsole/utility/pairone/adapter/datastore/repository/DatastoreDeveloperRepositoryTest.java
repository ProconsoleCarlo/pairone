package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.exception.EntityNotFoundException;
import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.adapter.datastore.model.TeamEntity;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.DeveloperAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.DeveloperEntityRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.TeamEntityRepository;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatastoreDeveloperRepositoryTest {
  private static final long TEAM_ID = 123L;
  private static final TeamEntity TEAM = new TeamEntity(TEAM_ID, "Team name");

  @Mock
  private DeveloperEntityRepository developerEntityRepository;
  @Mock
  private TeamEntityRepository teamEntityRepository;
  @Mock
  private DeveloperAdapter developerAdapter;

  private DeveloperRepository repository;

  @BeforeEach
  void setUp() {
    repository = new DatastoreDeveloperRepository(developerEntityRepository, teamEntityRepository, developerAdapter);
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

  @Nested
  class SaveTest {
    @Test
    void onCreateWithTeam() {
      var domainToCreate = new Developer("Dev1", TEAM_ID);
      var entityToCreate = new DeveloperEntity("Dev1", TEAM_ID);
      var createdEntity = new DeveloperEntity(1L, "Dev1", TEAM_ID);
      var createdDomain = new Developer(1L, "Dev1", TEAM_ID);

      when(teamEntityRepository.findById(TEAM_ID)).thenReturn(Optional.of(TEAM));
      when(developerAdapter.fromDomain(domainToCreate)).thenReturn(entityToCreate);
      when(developerEntityRepository.save(entityToCreate)).thenReturn(createdEntity);
      when(developerAdapter.toDomain(createdEntity)).thenReturn(createdDomain);

      assertEquals(createdDomain, repository.save(domainToCreate));
    }

    @Test
    void onCreateWithoutTeam() {
      var domainToCreate = new Developer("Dev1");
      var entityToCreate = new DeveloperEntity("Dev1");
      var createdEntity = new DeveloperEntity(1L, "Dev1");
      var createdDomain = new Developer(1L, "Dev1");

      when(developerAdapter.fromDomain(domainToCreate)).thenReturn(entityToCreate);
      when(developerEntityRepository.save(entityToCreate)).thenReturn(createdEntity);
      when(developerAdapter.toDomain(createdEntity)).thenReturn(createdDomain);

      assertEquals(createdDomain, repository.save(domainToCreate));
      verifyNoInteractions(teamEntityRepository);
    }

    @Test
    void onUpdateWithTeam() {
      var domainToUpdate = new Developer(1L, "Dev1", TEAM_ID);
      var entityToUpdate = new DeveloperEntity(1L, "Dev1", TEAM_ID);
      var updatedEntity = new DeveloperEntity(1L, "The Dev1", TEAM_ID);
      var updatedDomain = new Developer(1L, "The Dev1", TEAM_ID);

      when(teamEntityRepository.findById(TEAM_ID)).thenReturn(Optional.of(TEAM));
      when(developerAdapter.fromDomain(domainToUpdate)).thenReturn(entityToUpdate);
      when(developerEntityRepository.save(entityToUpdate)).thenReturn(updatedEntity);
      when(developerAdapter.toDomain(updatedEntity)).thenReturn(updatedDomain);

      assertEquals(updatedDomain, repository.save(domainToUpdate));
    }

    @Test
    void onUpdateWithoutTeam() {
      var domainToUpdate = new Developer(1L, "Dev1");
      var entityToUpdate = new DeveloperEntity(1L, "Dev1");
      var updatedEntity = new DeveloperEntity(1L, "The Dev1");
      var updatedDomain = new Developer(1L, "The Dev1");

      when(developerAdapter.fromDomain(domainToUpdate)).thenReturn(entityToUpdate);
      when(developerEntityRepository.save(entityToUpdate)).thenReturn(updatedEntity);
      when(developerAdapter.toDomain(updatedEntity)).thenReturn(updatedDomain);

      assertEquals(updatedDomain, repository.save(domainToUpdate));
      verifyNoInteractions(teamEntityRepository);
    }

    @Test
    void throwsWhenNotExistentTeam() {
      var domainToCreate = new Developer("Dev1", TEAM_ID);

      when(teamEntityRepository.findById(TEAM_ID)).thenReturn(Optional.empty());

      assertThrows(EntityNotFoundException.class, () -> repository.save(domainToCreate));
      verifyNoInteractions(developerAdapter, developerEntityRepository);
    }
  }
}