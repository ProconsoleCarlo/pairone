package it.proconsole.scheduler.logic;

import it.proconsole.scheduler.model.Match;
import it.proconsole.scheduler.model.Round;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleRoundRobinSchedulerTest {
  private static final Player P1 = new Player(1L);
  private static final Player P2 = new Player(2L);
  private static final Player P3 = new Player(3L);
  private static final Player P4 = new Player(4L);

  private final Scheduler<Player> scheduler = new CircleRoundRobinScheduler<>();

  @Test
  void scheduleForEvenNumber() {
    var players = List.of(P1, P2, P3, P4);

    var current = scheduler.scheduleFor(players);

    var expected = List.of(
            new Round<>(1L, List.of(new Match<>(1L, P1, P4), new Match<>(2L, P2, P3))),
            new Round<>(2L, List.of(new Match<>(1L, P1, P3), new Match<>(2L, P4, P2))),
            new Round<>(3L, List.of(new Match<>(1L, P1, P2), new Match<>(2L, P3, P4)))
    );

    assertEquals(expected, current);
  }

  @Test
  void scheduleForOddNumber() {
    var players = List.of(P1, P2, P3);

    var current = scheduler.scheduleFor(players);

    var expected = List.of(
            new Round<>(1L, List.of(new Match<>(1L, P1), new Match<>(2L, P2, P3))),
            new Round<>(2L, List.of(new Match<>(1L, P1, P3), new Match<>(2L, P2))),
            new Round<>(3L, List.of(new Match<>(1L, P1, P2), new Match<>(2L, P3)))
    );

    assertEquals(expected, current);
  }

  static class Player {
    private final Long id;

    public Player(Long id) {
      this.id = id;
    }

    public Long id() {
      return id;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Player)) return false;
      Player player = (Player) o;
      return id.equals(player.id);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id);
    }
  }
}