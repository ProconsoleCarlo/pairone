package it.proconsole.utility.pairone.adapter.datastore.repository.crud;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import it.proconsole.utility.pairone.adapter.datastore.model.PairEntity;

import java.util.List;

public interface PairEntityRepository extends DatastoreRepository<PairEntity, String> {
  List<PairEntity> findByTeamId(Long teamId);
}
