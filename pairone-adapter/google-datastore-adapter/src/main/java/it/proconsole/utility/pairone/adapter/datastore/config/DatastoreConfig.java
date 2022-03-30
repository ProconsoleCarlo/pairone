package it.proconsole.utility.pairone.adapter.datastore.config;

import com.google.cloud.spring.data.datastore.repository.config.EnableDatastoreRepositories;
import it.proconsole.utility.pairone.adapter.datastore.repository.DatastoreDeveloperRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.DatastorePairRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.DatastoreSprintRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.DatastoreTeamRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.DeveloperAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.PairAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.SprintAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.TeamAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.DeveloperEntityRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.PairEntityRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.SprintEntityRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.TeamEntityRepository;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import it.proconsole.utility.pairone.core.repository.PairRepository;
import it.proconsole.utility.pairone.core.repository.SprintRepository;
import it.proconsole.utility.pairone.core.repository.TeamRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDatastoreRepositories("it.proconsole.utility.pairone.adapter.datastore.repository.crud")
public class DatastoreConfig {
  @Bean
  public DeveloperRepository datastoreDeveloperRepository(
          DeveloperEntityRepository developerEntityRepository,
          TeamEntityRepository teamEntityRepository,
          DeveloperAdapter developerAdapter
  ) {
    return new DatastoreDeveloperRepository(
            developerEntityRepository,
            teamEntityRepository,
            developerAdapter
    );
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
  public PairRepository datastorePairRepository(
          PairEntityRepository pairEntityRepository,
          DeveloperEntityRepository developerEntityRepository,
          PairAdapter pairAdapter
  ) {
    return new DatastorePairRepository(
            pairEntityRepository,
            developerEntityRepository,
            pairAdapter
    );
  }

  @Bean
  public SprintRepository datastoreSprintRepository(
          SprintEntityRepository sprintEntityRepository,
          PairRepository datastorePairRepository,
          PairAdapter pairAdapter
  ) {
    return new DatastoreSprintRepository(
            sprintEntityRepository,
            datastorePairRepository,
            new SprintAdapter(),
            pairAdapter
    );
  }

  @Bean
  public PairAdapter pairAdapter(DeveloperAdapter developerAdapter) {
    return new PairAdapter(developerAdapter);
  }

  @Bean
  public DeveloperAdapter developerAdapter() {
    return new DeveloperAdapter();
  }
}
