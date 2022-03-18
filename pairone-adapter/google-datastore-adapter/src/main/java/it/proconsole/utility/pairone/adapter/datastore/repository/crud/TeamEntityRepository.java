package it.proconsole.utility.pairone.adapter.datastore.repository.crud;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import it.proconsole.utility.pairone.adapter.datastore.model.TeamEntity;

public interface TeamEntityRepository extends DatastoreRepository<TeamEntity, Long> {

}
