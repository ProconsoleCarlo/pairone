package it.proconsole.utility.pairone.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Pair {
  private final String id;
  private final List<Developer> members;

  @JsonCreator
  public Pair(String id, List<Developer> members) {
    this.id = id;
    this.members = new ArrayList<>(members);
  }

  @JsonGetter
  public String id() {
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
    return id.equals(pair.id) && members.equals(pair.members);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, members);
  }
}
