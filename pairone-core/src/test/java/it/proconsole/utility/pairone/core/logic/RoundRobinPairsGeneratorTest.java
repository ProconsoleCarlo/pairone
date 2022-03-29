package it.proconsole.utility.pairone.core.logic;

import it.proconsole.scheduler.logic.CircleRoundRobinScheduler;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.model.Sprint;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoundRobinPairsGeneratorTest {
  private static final Long TEAM_ID = 1L;

  @Mock
  private DeveloperRepository developerRepository;

  private PairsGenerator generator;

  @BeforeEach
  void setUp() {
    generator = new RoundRobinPairsGenerator(developerRepository, new CircleRoundRobinScheduler<>());
  }

  @Test
  void generateForOddNumber() {
    when(developerRepository.findByTeamId(TEAM_ID))
            .thenReturn(List.of(dev(1L), dev(2L), dev(3L)));

    var current = generator.generateFor(TEAM_ID);

    var expected = List.of(
            new Sprint(1L, List.of(new Pair(TEAM_ID, List.of(dev(1L))), new Pair(TEAM_ID, List.of(dev(2L), dev(3L))))),
            new Sprint(2L, List.of(new Pair(TEAM_ID, List.of(dev(1L), dev(3L))), new Pair(TEAM_ID, List.of(dev(2L))))),
            new Sprint(3L, List.of(new Pair(TEAM_ID, List.of(dev(1L), dev(2L))), new Pair(TEAM_ID, List.of(dev(3L)))))
    );

    assertEquals(expected, current);
  }

  private Developer dev(Long id) {
    return new Developer(id, "a");
  }
}