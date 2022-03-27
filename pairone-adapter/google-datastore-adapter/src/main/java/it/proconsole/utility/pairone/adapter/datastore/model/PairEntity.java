package it.proconsole.utility.pairone.adapter.datastore.model;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity(name = "pairs")
public class PairEntity {
  @Id
  private String id;
  private Long teamId;
  private Long firstDeveloperId;
  @Nullable
  private Long secondDeveloperId;

  public PairEntity(Long teamId, Long firstDeveloperId, @Nullable Long secondDeveloperId) {
    this.id = teamId + "|" + firstDeveloperId + "|" + secondDeveloperId;
    this.teamId = teamId;
    this.firstDeveloperId = firstDeveloperId;
    this.secondDeveloperId = secondDeveloperId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getTeamId() {
    return teamId;
  }

  public void setTeamId(Long teamId) {
    this.teamId = teamId;
  }

  public Long getFirstDeveloperId() {
    return firstDeveloperId;
  }

  public void setFirstDeveloperId(Long firstDeveloperId) {
    this.firstDeveloperId = firstDeveloperId;
  }

  @Nullable
  public Long getSecondDeveloperId() {
    return secondDeveloperId;
  }

  public void setSecondDeveloperId(@Nullable Long secondDeveloperId) {
    this.secondDeveloperId = secondDeveloperId;
  }

  public List<Long> developerIds() {
    return Optional.ofNullable(secondDeveloperId)
            .map(it -> List.of(firstDeveloperId, it))
            .orElseGet(() -> List.of(firstDeveloperId));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PairEntity)) return false;
    PairEntity that = (PairEntity) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
