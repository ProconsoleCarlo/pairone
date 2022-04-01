package it.proconsole.utility.pairone.adapter.datastore.repository.crud;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import it.proconsole.utility.pairone.adapter.datastore.model.PairEntity;

import java.util.List;

public interface PairEntityRepository extends DatastoreRepository<PairEntity, String> {
  void deleteAllBySprintId(List<Long> sprintIds);

  List<PairEntity> findBySprintId(Long sprintId);

  @Override
  <S extends PairEntity> List<S> saveAll(Iterable<S> entities);
}
