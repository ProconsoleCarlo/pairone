package it.proconsole.utility.pairone.core.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairTest {
  private static final Long A_DEVELOPER_ID = 1L;
  private static final Long ANOTHER_DEVELOPER_ID = 2L;

  @Test
  void firstId() {
    var pair = aPairWith(aDeveloperWith(A_DEVELOPER_ID), aDeveloperWith(ANOTHER_DEVELOPER_ID));

    assertEquals(A_DEVELOPER_ID, pair.firstId());
  }

  @Nested
  class SecondIdTest {
    @Test
    void secondId() {
      var pair = aPairWith(aDeveloperWith(A_DEVELOPER_ID), aDeveloperWith(ANOTHER_DEVELOPER_ID));

      assertEquals(Optional.of(ANOTHER_DEVELOPER_ID), pair.secondId());
    }

    @Test
    void emptyWhenPairIsSolo() {
      var pair = aPairWith(aDeveloperWith(A_DEVELOPER_ID));

      assertEquals(Optional.empty(), pair.secondId());
    }
  }

  private Developer aDeveloperWith(Long id) {
    return new Developer(id, "Dev nickName");
  }

  private Pair aPairWith(Developer... developers) {
    return new Pair("9|1|2", List.of(developers));
  }
}