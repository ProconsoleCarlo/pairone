package it.proconsole.utility.pairone.core.model;

import org.springframework.lang.Nullable;

public record Developer(@Nullable Long id, String nickName, @Nullable Long teamId) {
  public Developer(@Nullable Long id, String nickName) {
    this(id, nickName, null); //NOSONAR issue with record
  }

  public Developer(String nickName, @Nullable Long teamId) {
    this(null, nickName, teamId); //NOSONAR issue with record
  }

  public Developer(String nickName) {
    this(null, nickName);
  }
}
