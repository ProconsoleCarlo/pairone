package it.proconsole.utility.pairone.adapter.datastore.config;

import com.google.cloud.spring.data.datastore.repository.config.EnableDatastoreRepositories;
import it.proconsole.utility.pairone.adapter.datastore.repository.DatastoreDeveloperRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.DatastoreTeamRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.DeveloperAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.TeamAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.DeveloperEntityRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.TeamEntityRepository;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import it.proconsole.utility.pairone.core.repository.TeamRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDatastoreRepositories("it.proconsole.utility.pairone.adapter.datastore.repository.crud")
public class DatastoreConfig {
  @Bean
  public DeveloperRepository datastoreDeveloperRepository(
          DeveloperEntityRepository developerEntityRepository,
          DeveloperAdapter developerAdapter
  ) {
    return new DatastoreDeveloperRepository(developerEntityRepository, developerAdapter);
  }

  @Bean
  public TeamRepository datastoreTeamRepository(
          TeamEntityRepository teamEntityRepository,
          DeveloperEntityRepository developerEntityRepository,
          DeveloperAdapter developerAdapter
  ) {
    return new DatastoreTeamRepository(
            teamEntityRepository,
            developerEntityRepository,
            new TeamAdapter(developerAdapter),
            developerAdapter
    );
  }

  @Bean
  public DeveloperAdapter developerAdapter() {
    return new DeveloperAdapter();
  }
}
