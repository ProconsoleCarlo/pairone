package it.proconsole.utility.pairone.core.model;

import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;

public class Sprint {
  @Nullable
  private final Long id;
  private final Long teamId;
  private final Long round;
  private final List<Pair> pairs;

  public Sprint(@Nullable Long id, Long teamId, Long round, List<Pair> pairs) {
    this.id = id;
    this.teamId = teamId;
    this.round = round;
    this.pairs = pairs;
  }

  public Sprint(Long teamId, Long round, List<Pair> pairs) {
    this(null, teamId, round, pairs);
  }

  @Nullable
  public Long id() {
    return id;
  }

  public Long teamId() {
    return teamId;
  }

  public Long round() {
    return round;
  }

  public List<Pair> pairs() {
    return pairs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Sprint)) return false;
    Sprint sprint = (Sprint) o;
    return Objects.equals(id, sprint.id) && teamId.equals(sprint.teamId) && round.equals(sprint.round) && pairs.equals(sprint.pairs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, teamId, round, pairs);
  }
}
