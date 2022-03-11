package it.proconsole.utility.pairone.core.model;

import org.springframework.lang.Nullable;

import java.util.Objects;

public class Developer {
  @Nullable
  private final Long id;
  private final String nickName;

  public Developer(@Nullable Long id, String nickName) {
    this.id = id;
    this.nickName = nickName;
  }

  public Developer(String nickName) {
    this(null, nickName);
  }

  @Nullable
  public Long id() {
    return id;
  }

  public String nickName() {
    return nickName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Developer)) return false;
    Developer developer = (Developer) o;
    return Objects.equals(id, developer.id) && nickName.equals(developer.nickName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nickName);
  }
}
