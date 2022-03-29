package it.proconsole.utility.pairone.core.logic;

import it.proconsole.scheduler.logic.Scheduler;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.model.Sprint;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;

import java.util.List;
import java.util.stream.Collectors;

public class RoundRobinPairsGenerator implements PairsGenerator {
  private final DeveloperRepository developerRepository;
  private final Scheduler<Developer> scheduler;

  public RoundRobinPairsGenerator(DeveloperRepository developerRepository, Scheduler<Developer> scheduler) {
    this.developerRepository = developerRepository;
    this.scheduler = scheduler;
  }

  @Override
  public List<Sprint> generateFor(Long teamId) {
    var developers = developerRepository.findByTeamId(teamId);
    var rounds = scheduler.scheduleFor(developers);
    return rounds.stream()
            .flatMap(
                    round -> round.matches().stream()
                            .map(match ->
                                    new Sprint(
                                            round.id(),
                                            match.id(),
                                            match.secondPlayer()
                                                    .map(it -> new Pair(teamId, List.of(match.firstPlayer(), it)))
                                                    .orElseGet(() -> new Pair(teamId, List.of(match.firstPlayer())))
                                    )
                            )
            ).collect(Collectors.toList());
  }
}