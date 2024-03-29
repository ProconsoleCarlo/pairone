package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.core.model.Developer;

import java.util.List;

public class DeveloperAdapter {
  public List<DeveloperEntity> fromDomain(List<Developer> developers, Long teamId) {
    return developers.stream().map(it -> fromDomain(it, teamId)).toList();
  }

  public List<DeveloperEntity> fromDomain(List<Developer> developers) {
    return developers.stream().map(this::fromDomain).toList();
  }

  public DeveloperEntity fromDomain(Developer developer) {
    return new DeveloperEntity(developer.id(), developer.nickName(), developer.teamId());
  }

  public List<Developer> toDomain(List<DeveloperEntity> developers) {
    return developers.stream().map(this::toDomain).toList();
  }

  public Developer toDomain(DeveloperEntity developer) {
    return new Developer(developer.getId(), developer.getNickName(), developer.getTeamId());
  }

  private DeveloperEntity fromDomain(Developer developer, Long teamId) {
    return new DeveloperEntity(developer.id(), developer.nickName(), teamId);
  }
}
