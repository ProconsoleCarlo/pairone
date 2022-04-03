package it.proconsole.utility.pairone.adapter.datastore.repository.crud;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import it.proconsole.utility.pairone.adapter.datastore.model.SprintEntity;

import java.util.List;

public interface SprintEntityRepository extends DatastoreRepository<SprintEntity, String> {
  void deleteByTeamId(Long teamId);

  List<SprintEntity> findByTeamId(Long teamId);

  @Override
  <S extends SprintEntity> List<S> saveAll(Iterable<S> entities);
}
