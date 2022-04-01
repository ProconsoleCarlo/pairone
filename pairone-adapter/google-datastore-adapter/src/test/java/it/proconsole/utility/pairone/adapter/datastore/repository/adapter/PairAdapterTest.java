package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.PairEntity;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairAdapterTest {
  private static final Long PAIR_ID = 987L;
  private static final Long SPRINT_ID = 9L;
  private static final Long A_DEVELOPER_ID = 1L;
  private static final Long ANOTHER_DEVELOPER_ID = 2L;
  private static final List<Developer> DOMAIN_DEVELOPERS = List.of(
          new Developer(A_DEVELOPER_ID, "Dev1"),
          new Developer(ANOTHER_DEVELOPER_ID, "Dev2")
  );
  private static final PairEntity ENTITY = new PairEntity(PAIR_ID, SPRINT_ID, A_DEVELOPER_ID, ANOTHER_DEVELOPER_ID);
  private static final Pair DOMAIN = new Pair(PAIR_ID, DOMAIN_DEVELOPERS);

  private final PairAdapter adapter = new PairAdapter();

  @Test
  void fromDomain() {
    var current = adapter.fromDomain(List.of(DOMAIN), SPRINT_ID);

    assertEquals(List.of(ENTITY), current);
  }

  @Test
  void toDomain() {
    var current = adapter.toDomain(ENTITY, DOMAIN_DEVELOPERS);

    assertEquals(DOMAIN, current);
  }
}