package it.proconsole.utility.pairone.adapter.datastore.model;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import it.proconsole.utility.pairone.core.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Entity(name = "developers")
public class DeveloperEntity {
  @Id
  @Nullable
  private Long id;
  private String nickName;
  @Nullable
  private Long teamId;

  public DeveloperEntity() {
    this.nickName = "";
  }

  public DeveloperEntity(@Nullable Long id, String nickName, @Nullable Long teamId) {
    this.id = id;
    this.nickName = nickName;
    this.teamId = teamId;
  }

  public DeveloperEntity(@Nullable Long id, String nickName) {
    this.id = id;
    this.nickName = nickName;
  }

  public DeveloperEntity(String nickName, @Nullable Long teamId) {
    this.nickName = nickName;
    this.teamId = teamId;
  }

  public DeveloperEntity(String nickName) {
    this.nickName = nickName;
  }

  @Nullable
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  @Nullable
  public Long getTeamId() {
    return teamId;
  }

  public void setTeamId(Long teamId) {
    this.teamId = teamId;
  }

  @Generated
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DeveloperEntity that)) return false;
    return Objects.equals(id, that.id) && nickName.equals(that.nickName) && Objects.equals(teamId, that.teamId);
  }

  @Generated
  @Override
  public int hashCode() {
    return Objects.hash(id, nickName, teamId);
  }
}
