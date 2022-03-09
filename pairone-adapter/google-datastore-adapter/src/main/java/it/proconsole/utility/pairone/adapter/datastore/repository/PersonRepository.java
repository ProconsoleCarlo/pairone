package it.proconsole.utility.pairone.adapter.datastore.repository;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import it.proconsole.utility.pairone.adapter.datastore.model.Person;

import java.util.List;

public interface PersonRepository extends DatastoreRepository<Person, Long> {
  List<Person> findAll();
}
