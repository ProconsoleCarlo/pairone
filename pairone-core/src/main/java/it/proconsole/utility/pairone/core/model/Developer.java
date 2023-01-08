package it.proconsole.utility.pairone.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.lang.Nullable;

import javax.annotation.processing.Generated;
import java.util.Objects;

public class Developer {
  @Nullable
  private final Long id;
  private final String nickName;
  @Nullable
  private final Long teamId;

  @JsonCreator
  public Developer(@Nullable Long id, String nickName, @Nullable Long teamId) {
    this.id = id;
    this.nickName = nickName;
    this.teamId = teamId;
  }

  public Developer(@Nullable Long id, String nickName) {
    this(id, nickName, null);
  }

  public Developer(String nickName, @Nullable Long teamId) {
    this(null, nickName, teamId);
  }

  public Developer(String nickName) {
    this(null, nickName);
  }

  @JsonGetter
  @Nullable
  public Long id() {
    return id;
  }

  @JsonGetter
  public String nickName() {
    return nickName;
  }

  @JsonGetter
  @Nullable
  public Long teamId() {
    return teamId;
  }

  @Generated(value = "java.util.Objects.equals")
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Developer developer)) return false;
    return Objects.equals(id, developer.id) && nickName.equals(developer.nickName) && Objects.equals(teamId, developer.teamId);
  }

  @Generated(value = "java.util.Objects.hash")
  @Override
  public int hashCode() {
    return Objects.hash(id, nickName, teamId);
  }

  @Override
  public String toString() {
    return "Developer{" +
            "id=" + id +
            ", nickName='" + nickName + '\'' +
            ", teamId=" + teamId +
            '}';
  }
}
