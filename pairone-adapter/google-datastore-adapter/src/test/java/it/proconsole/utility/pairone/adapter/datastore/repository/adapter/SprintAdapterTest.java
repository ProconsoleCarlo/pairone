package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.SprintEntity;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.model.Sprint;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SprintAdapterTest {
  private static final Long ROUND = 1L;
  private static final Long TEAM_ID = 987L;
  private static final Long SPRINT_ID = 9L;
  private static final Long ANOTHER_SPRINT_ID = 9L;
  private static final Developer A_DEVELOPER = new Developer(1L, "Dev1");
  private static final Developer ANOTHER_DEVELOPER = new Developer(2L, "Dev1");
  private static final List<Pair> PAIRS = List.of(
          new Pair(List.of(A_DEVELOPER, ANOTHER_DEVELOPER)),
          new Pair(List.of(ANOTHER_DEVELOPER, A_DEVELOPER))
  );
  private static final SprintEntity ENTITY = new SprintEntity(SPRINT_ID, TEAM_ID, ROUND);
  private static final Sprint DOMAIN = new Sprint(SPRINT_ID, ROUND, PAIRS);
  private static final List<SprintEntity> ENTITIES = List.of(
          new SprintEntity(SPRINT_ID, TEAM_ID, ROUND),
          new SprintEntity(ANOTHER_SPRINT_ID, TEAM_ID, ROUND)
  );
  private static final List<Sprint> DOMAINS = List.of(
          new Sprint(SPRINT_ID, ROUND, PAIRS),
          new Sprint(ANOTHER_SPRINT_ID, ROUND, PAIRS)
  );


  private final SprintAdapter adapter = new SprintAdapter();

  @Nested
  class FromDomain {
    @Test
    void multipleEntities() {
      assertEquals(ENTITIES, adapter.fromDomain(DOMAINS, TEAM_ID));
    }

    @Test
    void singleEntity() {
      assertEquals(ENTITY, adapter.fromDomain(DOMAIN, TEAM_ID));
    }
  }

  @Test
  void toDomain() {
    assertEquals(DOMAIN, adapter.toDomain(ENTITY, PAIRS));
  }
}