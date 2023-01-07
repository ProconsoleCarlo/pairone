package it.proconsole.utility.pairone.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;

public class Sprint {
  @Nullable
  private final Long id;
  private final Long round;
  private final List<Pair> pairs;

  @JsonCreator
  public Sprint(@Nullable Long id, Long round, List<Pair> pairs) {
    this.id = id;
    this.round = round;
    this.pairs = pairs;
  }

  public Sprint(Long round, List<Pair> pairs) {
    this(null, round, pairs);
  }

  @JsonGetter
  @Nullable
  public Long id() {
    return id;
  }

  @JsonGetter
  public Long round() {
    return round;
  }

  @JsonGetter
  public List<Pair> pairs() {
    return pairs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Sprint sprint)) return false;
    return Objects.equals(id, sprint.id) && round.equals(sprint.round) && pairs.equals(sprint.pairs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, round, pairs);
  }
}
