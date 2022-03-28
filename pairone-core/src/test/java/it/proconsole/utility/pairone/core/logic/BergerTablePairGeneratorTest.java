package it.proconsole.utility.pairone.core.logic;

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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BergerTablePairGeneratorTest {
  private static final Long TEAM_ID = 1L;

  @Mock
  private DeveloperRepository developerRepository;

  private PairsGenerator generator;

  @BeforeEach
  void setUp() {
    generator = new BergerTablePairGenerator(developerRepository);
  }

  @Test
  void generateForEvenNumber() {
    when(developerRepository.findByTeamId(TEAM_ID))
            .thenReturn(List.of(
                    new Developer(1L, "a"),
                    new Developer(2L, "a"),
                    new Developer(3L, "a"),
                    new Developer(4L, "a")
            ));

    var current = generator.generateFor(TEAM_ID);

    var expected = List.of(
            new Pair("", List.of(new Developer(1L, "a"), new Developer(2L, "a"))),
            new Pair("", List.of(new Developer(1L, "a"), new Developer(3L, "a"))),
            new Pair("", List.of(new Developer(1L, "a"), new Developer(4L, "a"))),
            new Pair("", List.of(new Developer(2L, "a"), new Developer(3L, "a"))),
            new Pair("", List.of(new Developer(2L, "a"), new Developer(4L, "a"))),
            new Pair("", List.of(new Developer(3L, "a"), new Developer(4L, "a")))
    );
    assertEquals(expected, current);
  }

  @Test
  void generateForOddNumber() {
    when(developerRepository.findByTeamId(TEAM_ID))
            .thenReturn(List.of(
                    new Developer(1L, "a"),
                    new Developer(2L, "a"),
                    new Developer(3L, "a")
            ));

    var current = generator.generateFor(TEAM_ID);

    var expected = List.of(
            new Sprint(3L, 1L, new Pair("", List.of(new Developer(1L, "a"), new Developer(2L, "a")))),
            new Sprint(2L, 1L, new Pair("", List.of(new Developer(1L, "a"), new Developer(99L, "DUMMY")))),
            new Sprint(1L, 1L, new Pair("", List.of(new Developer(1L, "a"), new Developer(3L, "a")))),
            new Sprint(1L, 2L, new Pair("", List.of(new Developer(2L, "a"), new Developer(99L, "DUMMY")))),
            new Sprint(2L, 2L, new Pair("", List.of(new Developer(3L, "a"), new Developer(2L, "a")))),
            new Sprint(3L, 2L, new Pair("", List.of(new Developer(99L, "DUMMY"), new Developer(3L, "a"))))
    );

    assertTrue(current.containsAll(expected));
  }
}