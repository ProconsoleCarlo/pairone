package it.proconsole.utility.pairone.adapter.datastore.model;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Entity(name = "teams")
public class TeamEntity {
  @Id
  @Nullable
  private Long id;
  private String name;

  public TeamEntity(@Nullable Long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Nullable
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TeamEntity)) return false;
    TeamEntity that = (TeamEntity) o;
    return Objects.equals(id, that.id) && name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
