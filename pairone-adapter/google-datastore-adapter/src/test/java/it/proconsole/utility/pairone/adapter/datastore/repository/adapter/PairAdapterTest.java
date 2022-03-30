package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.adapter.datastore.model.PairEntity;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PairAdapterTest {
  private static final Long PAIR_ID = 987L;
  private static final Long SPRINT_ID = 9L;
  private static final Long A_DEVELOPER_ID = 1L;
  private static final Long ANOTHER_DEVELOPER_ID = 2L;
  private static final List<DeveloperEntity> DEVELOPER_ENTITIES = List.of(
          new DeveloperEntity(A_DEVELOPER_ID, "Dev1", SPRINT_ID),
          new DeveloperEntity(ANOTHER_DEVELOPER_ID, "Dev2", SPRINT_ID)
  );
  private static final List<Developer> DOMAIN_DEVELOPERS = List.of(
          new Developer(A_DEVELOPER_ID, "Dev1"),
          new Developer(ANOTHER_DEVELOPER_ID, "Dev2")
  );
  private static final PairEntity ENTITY = new PairEntity(PAIR_ID, SPRINT_ID, A_DEVELOPER_ID, ANOTHER_DEVELOPER_ID);
  private static final Pair DOMAIN = new Pair(PAIR_ID, DOMAIN_DEVELOPERS);

  @Mock
  private DeveloperAdapter developerAdapter;

  private PairAdapter adapter;

  @BeforeEach
  void setUp() {
    adapter = new PairAdapter(developerAdapter);
  }

  @Test
  void fromDomain() {
    var current = adapter.fromDomain(List.of(DOMAIN), SPRINT_ID);

    assertEquals(List.of(ENTITY), current);
  }

  @Test
  void toDomain() {
    when(developerAdapter.toDomain(DEVELOPER_ENTITIES)).thenReturn(DOMAIN_DEVELOPERS);

    var current = adapter.toDomain(ENTITY, DEVELOPER_ENTITIES);

    assertEquals(DOMAIN, current);
  }
}