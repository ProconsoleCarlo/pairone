package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.core.model.Developer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeveloperAdapterTest {
  private static final List<DeveloperEntity> ENTITIES = List.of(
          new DeveloperEntity(1L, "Dev1"),
          new DeveloperEntity("Dev2")
  );
  private static final List<Developer> DOMAIN = List.of(
          new Developer(1L, "Dev1"),
          new Developer("Dev2")
  );

  private final DeveloperAdapter adapter = new DeveloperAdapter();

  @Nested
  class FromDomain {
    @Test
    void multipleEntitiesWithTeamId() {
      var domain = List.of(new Developer("Dev1"));
      var entities = List.of(new DeveloperEntity("Dev1", 123L));

      assertEquals(entities, adapter.fromDomain(domain, 123L));
    }

    @Test
    void multipleEntities() {
      assertEquals(ENTITIES, adapter.fromDomain(DOMAIN));
    }

    @Test
    void singleEntity() {
      assertEquals(ENTITIES.get(0), adapter.fromDomain(DOMAIN.get(0)));
    }
  }

  @Nested
  class ToDomain {
    @Test
    void multipleEntities() {
      assertEquals(DOMAIN, adapter.toDomain(ENTITIES));
    }

    @Test
    void singleEntity() {
      assertEquals(DOMAIN.get(0), adapter.toDomain(ENTITIES.get(0)));
    }
  }
}