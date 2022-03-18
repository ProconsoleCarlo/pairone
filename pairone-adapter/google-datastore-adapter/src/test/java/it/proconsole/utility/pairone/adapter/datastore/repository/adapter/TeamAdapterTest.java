package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.adapter.datastore.model.TeamEntity;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamAdapterTest {
  private static final List<DeveloperEntity> DEVELOPER_ENTITIES = List.of(
          new DeveloperEntity(1L, "Dev1"),
          new DeveloperEntity("Dev2")
  );
  private static final List<Developer> DOMAIN_DEVELOPERS = List.of(
          new Developer(1L, "Dev1"),
          new Developer("Dev2")
  );
  private static final TeamEntity ENTITY = new TeamEntity(1L, "An awesome team name");
  private static final Team DOMAIN = new Team(1L, "An awesome team name", DOMAIN_DEVELOPERS);

  @Mock
  private DeveloperAdapter developerAdapter;

  private TeamAdapter adapter;

  @BeforeEach
  void setUp() {
    adapter = new TeamAdapter(developerAdapter);
  }

  @Test
  void fromDomain() {
    assertEquals(ENTITY, adapter.fromDomain(DOMAIN));
    verifyNoInteractions(developerAdapter);
  }

  @Test
  void toDomain() {
    when(developerAdapter.toDomain(DEVELOPER_ENTITIES)).thenReturn(DOMAIN_DEVELOPERS);

    assertEquals(DOMAIN, adapter.toDomain(ENTITY, DEVELOPER_ENTITIES));
  }
}