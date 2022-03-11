package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.core.model.Developer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeveloperAdapter {
  public List<DeveloperEntity> fromDomain(List<Developer> developers) {
    return developers.stream().map(this::fromDomain).collect(Collectors.toList());
  }

  public List<Developer> toDomain(List<DeveloperEntity> developers) {
    return developers.stream().map(this::toDomain).collect(Collectors.toList());
  }

  private DeveloperEntity fromDomain(Developer developer) {
    var entity = new DeveloperEntity();
    Optional.ofNullable(developer.id()).ifPresent(entity::setId);
    entity.setNickName(developer.nickName());
    return entity;
  }

  private Developer toDomain(DeveloperEntity developer) {
    return new Developer(developer.getId(), developer.getNickName());
  }
}
