package it.proconsole.utility.pairone.adapter.datastore.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairEntityTest {
  private static final Long SPRINT_ID = 9L;
  private static final Long A_DEVELOPER_ID = 1L;
  private static final Long ANOTHER_DEVELOPER_ID = 2L;

  @Test
  void developerIdsWhenFirstAndSecondArePresent() {
    var pairEntity = new PairEntity(SPRINT_ID, 1L, A_DEVELOPER_ID, ANOTHER_DEVELOPER_ID);

    assertEquals(List.of(A_DEVELOPER_ID, ANOTHER_DEVELOPER_ID), pairEntity.developerIds());
  }

  @Test
  void developerIdsWhenSecondIsNotPresent() {
    var pairEntity = new PairEntity(SPRINT_ID, 1L, A_DEVELOPER_ID, null);

    assertEquals(List.of(A_DEVELOPER_ID), pairEntity.developerIds());
  }
}