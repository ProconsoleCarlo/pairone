package it.proconsole.scheduler.logic;

import it.proconsole.scheduler.model.Match;
import it.proconsole.scheduler.model.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CircleRoundRobinSchedulerTest {
  private Scheduler<Player> scheduler;

  @BeforeEach
  void setUp() {
    scheduler = new CircleRoundRobinScheduler<>();
  }

  @Test
  void scheduleForEvenNumber() {
    var players = List.of(new Player(1L), new Player(2L), new Player(3L), new Player(4L));

    var current = scheduler.scheduleFor(players);

    var expected = List.of(
            new Round<>(1L, List.of(new Match<>(1L, new Player(1L), new Player(3L)), new Match<>(2L, new Player(2L), new Player(4L)))),
            new Round<>(2L, List.of(new Match<>(1L, new Player(1L), new Player(4L)), new Match<>(2L, new Player(3L), new Player(2L)))),
            new Round<>(3L, List.of(new Match<>(1L, new Player(1L), new Player(2L)), new Match<>(2L, new Player(4L), new Player(3L))))
    );

    assertTrue(current.containsAll(expected));
  }

  @Test
  void scheduleForOddNumber() {
    var players = List.of(new Player(1L), new Player(2L), new Player(3L));

    var current = scheduler.scheduleFor(players);

    var expected = List.of(
            new Round<>(1L, List.of(new Match<>(1L, new Player(1L), new Player(3L)), new Match<>(2L, new Player(2L)))),
            new Round<>(2L, List.of(new Match<>(1L, new Player(1L)), new Match<>(2L, new Player(3L), new Player(2L)))),
            new Round<>(3L, List.of(new Match<>(1L, new Player(1L), new Player(2L)), new Match<>(2L, new Player(3L))))
    );

    assertTrue(current.containsAll(expected));
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