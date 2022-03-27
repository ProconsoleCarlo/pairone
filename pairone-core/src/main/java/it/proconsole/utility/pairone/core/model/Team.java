package it.proconsole.utility.pairone.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Team {
  @Nullable
  private final Long id;
  private final String name;
  private final List<Developer> developers;

  @JsonCreator
  public Team(@Nullable Long id, String name, List<Developer> developers) {
    this.id = id;
    this.name = name;
    this.developers = developers;
  }

  public Team(Long id, String name) {
    this(id, name, Collections.emptyList());
  }

  @JsonGetter
  @Nullable
  public Long id() {
    return id;
  }

  @JsonGetter
  public String name() {
    return name;
  }

  @JsonGetter
  public List<Developer> developers() {
    return developers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Team)) return false;
    Team team = (Team) o;
    return Objects.equals(id, team.id) && name.equals(team.name) && developers.equals(team.developers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, developers);
  }
}
