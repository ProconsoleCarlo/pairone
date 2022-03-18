package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.adapter.datastore.model.TeamEntity;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.DeveloperAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.TeamAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.DeveloperEntityRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.TeamEntityRepository;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Team;
import it.proconsole.utility.pairone.core.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatastoreTeamRepositoryTest {
  private static final Long TEAM_ID = 123L;
  private static final List<DeveloperEntity> DEVELOPER_ENTITIES = List.of(new DeveloperEntity(1L, "Dev1"));
  private static final List<Developer> DEVELOPERS = List.of(new Developer(1L, "Dev1"));

  @Mock
  private TeamEntityRepository teamEntityRepository;
  @Mock
  private DeveloperEntityRepository developerEntityRepository;
  @Mock
  private TeamAdapter teamAdapter;
  @Mock
  private DeveloperAdapter developerAdapter;

  private TeamRepository repository;

  @BeforeEach
  void setUp() {
    repository = new DatastoreTeamRepository(
            teamEntityRepository,
            developerEntityRepository,
            teamAdapter,
            developerAdapter
    );
  }

  @Test
  void findById() {
    var domainTeam = new Team(TEAM_ID, "An awesome team name", DEVELOPERS);
    var entityTeam = new TeamEntity(TEAM_ID, "An awesome team name");
    when(teamEntityRepository.findById(TEAM_ID)).thenReturn(Optional.of(entityTeam));
    when(developerEntityRepository.findByTeamId(TEAM_ID)).thenReturn(DEVELOPER_ENTITIES);
    when(teamAdapter.toDomain(entityTeam, DEVELOPER_ENTITIES)).thenReturn(domainTeam);

    var currentTeam = repository.findById(TEAM_ID);

    assertEquals(Optional.of(domainTeam), currentTeam);
  }

  @Test
  void save() {
    var domainTeam = new Team(TEAM_ID, "An awesome team name", DEVELOPERS);
    var entityTeam = new TeamEntity(TEAM_ID, "An awesome team name");
    when(teamAdapter.fromDomain(domainTeam)).thenReturn(entityTeam);
    when(teamEntityRepository.save(entityTeam)).thenReturn(entityTeam);
    when(developerAdapter.fromDomain(DEVELOPERS, TEAM_ID)).thenReturn(DEVELOPER_ENTITIES);
    when(developerEntityRepository.saveAll(DEVELOPER_ENTITIES)).thenReturn(DEVELOPER_ENTITIES);
    when(teamAdapter.toDomain(entityTeam, DEVELOPER_ENTITIES)).thenReturn(domainTeam);

    var currentTeam = repository.save(domainTeam);

    assertEquals(domainTeam, currentTeam);
  }
}