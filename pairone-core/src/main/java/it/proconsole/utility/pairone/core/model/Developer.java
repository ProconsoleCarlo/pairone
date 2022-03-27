package it.proconsole.utility.pairone.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.lang.Nullable;

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
    this.id = id;
    this.nickName = nickName;
    this.teamId = null;
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

  public Builder copy() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Developer)) return false;
    Developer developer = (Developer) o;
    return Objects.equals(id, developer.id) && nickName.equals(developer.nickName) && Objects.equals(teamId, developer.teamId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nickName, teamId);
  }

  public static class Builder {
    @Nullable
    private Long id;
    private String nickName;

    public Builder(Developer developer) {
      this.id = developer.id;
      this.nickName = developer.nickName;
    }

    public Builder withId(@Nullable Long id) {
      this.id = id;
      return this;
    }

    public Builder withNickName(String nickName) {
      this.nickName = nickName;
      return this;
    }

    public Developer build() {
      return new Developer(id, nickName);
    }
  }
}
