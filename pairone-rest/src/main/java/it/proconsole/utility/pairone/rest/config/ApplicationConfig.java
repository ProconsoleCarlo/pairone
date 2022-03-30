package it.proconsole.utility.pairone.rest.config;

import it.proconsole.scheduler.logic.CircleRoundRobinScheduler;
import it.proconsole.utility.pairone.core.logic.PairsGenerator;
import it.proconsole.utility.pairone.core.logic.RoundRobinPairsGenerator;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
  @Bean
  public PairsGenerator pairsGenerator(DeveloperRepository datastoreDeveloperRepository) {
    return new RoundRobinPairsGenerator(datastoreDeveloperRepository, new CircleRoundRobinScheduler<>());
  }
}
