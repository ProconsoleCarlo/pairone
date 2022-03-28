package it.proconsole.utility.pairone.core.model;

import java.util.Objects;

public class Sprint {
  private final Long round;
  private final Long game;
  private final Pair pairs;

  public Sprint(Long round, Long game, Pair pairs) {
    this.round = round;
    this.game = game;
    this.pairs = pairs;
  }

  public Long getRound() {
    return round;
  }

  public Long getGame() {
    return game;
  }

  public Pair getPairs() {
    return pairs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Sprint)) return false;
    Sprint sprint = (Sprint) o;
    return round.equals(sprint.round) && game.equals(sprint.game) && pairs.equals(sprint.pairs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(round, game, pairs);
  }

  @Override
  public String toString() {
    return "Sprint{" +
            "round=" + round +
            ", game=" + game +
            ", pairs=" + pairs +
            '}';
  }
}
