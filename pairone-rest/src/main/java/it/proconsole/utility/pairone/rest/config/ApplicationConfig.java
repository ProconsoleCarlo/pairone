package it.proconsole.utility.pairone.rest.config;

import it.proconsole.scheduler.logic.CircleRoundRobinScheduler;
import it.proconsole.utility.pairone.core.logic.RoundRobinSprintsGenerator;
import it.proconsole.utility.pairone.core.logic.SprintsGenerator;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import it.proconsole.utility.pairone.core.repository.SprintRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
  @Bean
  public SprintsGenerator pairsGenerator(
          DeveloperRepository datastoreDeveloperRepository,
          SprintRepository datastoreSprintRepository
  ) {
    return new RoundRobinSprintsGenerator(
            datastoreDeveloperRepository,
            datastoreSprintRepository,
            new CircleRoundRobinScheduler<>()
    );
  }
}
