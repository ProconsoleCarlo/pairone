package it.proconsole.utility.pairone.adapter.datastore.repository.crud;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import it.proconsole.utility.pairone.adapter.datastore.model.TeamEntity;

import java.util.List;

public interface TeamEntityRepository extends DatastoreRepository<TeamEntity, Long> {
  List<TeamEntity> findAll();

  <S extends TeamEntity> List<S> saveAll(Iterable<S> entities);
}
