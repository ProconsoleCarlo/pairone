package it.proconsole.utility.pairone.adapter.datastore.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairEntityTest {
  private static final Long A_DEVELOPER_ID = 1L;
  private static final Long ANOTHER_DEVELOPER_ID = 2L;

  @Test
  void generateUniqueIdAsKey() {
    var aPairEntity = new PairEntity(1L, A_DEVELOPER_ID, ANOTHER_DEVELOPER_ID);
    var anotherPairEntity = new PairEntity(1L, A_DEVELOPER_ID, null);

    assertEquals("1|1|2", aPairEntity.getId());
    assertEquals("1|1|null", anotherPairEntity.getId());
  }

  @Test
  void developerIdsWhenFirstAndSecondArePresent() {
    var pairEntity = new PairEntity(1L, A_DEVELOPER_ID, ANOTHER_DEVELOPER_ID);

    assertEquals(List.of(A_DEVELOPER_ID, ANOTHER_DEVELOPER_ID), pairEntity.developerIds());
  }

  @Test
  void developerIdsWhenSecondIsNotPresent() {
    var pairEntity = new PairEntity(1L, A_DEVELOPER_ID, null);

    assertEquals(List.of(A_DEVELOPER_ID), pairEntity.developerIds());
  }
}