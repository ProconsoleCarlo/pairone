package it.proconsole.utility.pairone.core.model;

import java.util.List;
import java.util.Objects;

public class Sprint {
  private final Long round;
  private final List<Pair> pairs;

  public Sprint(Long round, List<Pair> pairs) {
    this.round = round;
    this.pairs = pairs;
  }

  public Long getRound() {
    return round;
  }

  public List<Pair> getPairs() {
    return pairs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Sprint)) return false;
    Sprint sprint = (Sprint) o;
    return round.equals(sprint.round) && pairs.equals(sprint.pairs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(round, pairs);
  }
}
