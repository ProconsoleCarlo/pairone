package it.proconsole.utility.pairone.adapter.datastore.model;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

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
}
