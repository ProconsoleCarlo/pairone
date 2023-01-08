package it.proconsole.utility.pairone.adapter.datastore.model;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import it.proconsole.utility.pairone.core.util.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity(name = "pairs")
public class PairEntity {
  @Id
  @Nullable
  private Long id;
  private Long sprintId;
  private Long firstDeveloperId;
  @Nullable
  private Long secondDeveloperId;

  public PairEntity(@Nullable Long id, Long sprintId, Long firstDeveloperId, @Nullable Long secondDeveloperId) {
    this.id = id;
    this.sprintId = sprintId;
    this.firstDeveloperId = firstDeveloperId;
    this.secondDeveloperId = secondDeveloperId;
  }

  @Nullable
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSprintId() {
    return sprintId;
  }

  public void setSprintId(Long sprintId) {
    this.sprintId = sprintId;
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

  @Generated
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PairEntity that)) return false;
    return Objects.equals(id, that.id) && sprintId.equals(that.sprintId) && firstDeveloperId.equals(that.firstDeveloperId) && Objects.equals(secondDeveloperId, that.secondDeveloperId);
  }

  @Generated
  @Override
  public int hashCode() {
    return Objects.hash(id, sprintId, firstDeveloperId, secondDeveloperId);
  }
}
