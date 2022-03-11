package it.proconsole.utility.pairone.adapter.datastore.model;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Entity
public class DeveloperEntity {
  @Id
  private Long id;
  private String nickName;

  public DeveloperEntity() {
  }

  public DeveloperEntity(Long id, String nickName) {
    this.id = id;
    this.nickName = nickName;
  }

  public DeveloperEntity(String nickName) {
    this.nickName = nickName;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DeveloperEntity)) return false;
    DeveloperEntity that = (DeveloperEntity) o;
    return Objects.equals(id, that.id) && nickName.equals(that.nickName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nickName);
  }
}
