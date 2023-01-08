package it.proconsole.utility.pairone.adapter.datastore.model;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import it.proconsole.utility.pairone.core.util.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Entity(name = "sprints")
public class SprintEntity {
  @Id
  @Nullable
  private Long id;
  private Long teamId;
  private Long round;

  public SprintEntity(@Nullable Long id, Long teamId, Long round) {
    this.id = id;
    this.teamId = teamId;
    this.round = round;
  }

  @Nullable
  public Long getId() {
    return id;
  }

  public void setId(@Nullable Long id) {
    this.id = id;
  }

  public Long getTeamId() {
    return teamId;
  }

  public void setTeamId(Long teamId) {
    this.teamId = teamId;
  }

  public Long getRound() {
    return round;
  }

  public void setRound(Long round) {
    this.round = round;
  }

  @Generated
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SprintEntity that)) return false;
    return Objects.equals(id, that.id) && teamId.equals(that.teamId) && round.equals(that.round);
  }

  @Generated
  @Override
  public int hashCode() {
    return Objects.hash(id, teamId, round);
  }
}
