package it.proconsole.scheduler.model;

import java.util.List;
import java.util.Objects;

public class Round<P> {
  private final Long id;
  private final List<Match<P>> matches;

  public Round(Long id, List<Match<P>> matches) {
    this.id = id;
    this.matches = matches;
  }

  public Long id() {
    return id;
  }

  public List<Match<P>> matches() {
    return matches;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Round)) return false;
    Round<?> round = (Round<?>) o;
    return id.equals(round.id) && matches.equals(round.matches);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, matches);
  }
}
