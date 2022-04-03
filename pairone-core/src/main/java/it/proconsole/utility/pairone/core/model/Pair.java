package it.proconsole.utility.pairone.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Pair {
  @Nullable
  private final Long id;
  private final List<Developer> members;

  @JsonCreator
  public Pair(@Nullable Long id, List<Developer> members) {
    this.id = id;
    this.members = new ArrayList<>(members);
  }

  public Pair(List<Developer> members) {
    this(null, members);
  }

  @JsonGetter
  @Nullable
  public Long id() {
    return id;
  }

  @JsonGetter
  public List<Developer> members() {
    return members;
  }

  public Long firstId() {
    return Objects.requireNonNull(members.get(0).id());
  }

  public Optional<Long> secondId() {
    return members.size() > 1
            ? Optional.ofNullable(members.get(1).id())
            : Optional.empty();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Pair)) return false;
    Pair pair = (Pair) o;
    return Objects.equals(id, pair.id) && members.equals(pair.members);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, members);
  }

  @Override
  public String toString() {
    return "Pair{" +
            "id='" + id + '\'' +
            ", members=" + members +
            '}';
  }
}
