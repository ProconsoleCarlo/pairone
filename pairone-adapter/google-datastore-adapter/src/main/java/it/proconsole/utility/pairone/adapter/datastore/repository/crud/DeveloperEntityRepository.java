package it.proconsole.utility.pairone.adapter.datastore.repository.crud;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;

import java.util.List;

public interface DeveloperEntityRepository extends DatastoreRepository<DeveloperEntity, Long> {
  List<DeveloperEntity> findAll();

  List<DeveloperEntity> findByTeamId(Long teamId);

  <S extends DeveloperEntity> List<S> saveAll(Iterable<S> entities);
}
