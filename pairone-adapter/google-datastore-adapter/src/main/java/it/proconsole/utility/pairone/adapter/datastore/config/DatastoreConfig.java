package it.proconsole.utility.pairone.adapter.datastore.config;

import com.google.cloud.spring.data.datastore.repository.config.EnableDatastoreRepositories;
import it.proconsole.utility.pairone.adapter.datastore.repository.DatastoreDeveloperRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.DeveloperAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.DeveloperEntityRepository;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDatastoreRepositories("it.proconsole.utility.pairone.adapter.datastore.repository.crud")
public class DatastoreConfig {
  @Bean
  public DeveloperRepository datastoreDeveloperRepository(
          DeveloperEntityRepository developerEntityRepository
  ) {
    return new DatastoreDeveloperRepository(developerEntityRepository, new DeveloperAdapter());
  }
}
