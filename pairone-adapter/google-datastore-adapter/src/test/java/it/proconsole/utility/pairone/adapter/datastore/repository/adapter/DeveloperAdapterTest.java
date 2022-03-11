package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.core.model.Developer;
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

  @Test
  void fromDomain() {
    assertEquals(ENTITIES, adapter.fromDomain(DOMAIN));
  }

  @Test
  void toDomain() {
    assertEquals(DOMAIN, adapter.toDomain(ENTITIES));
  }
}