package it.proconsole.utility.pairone.core.logic;

import it.proconsole.scheduler.logic.Scheduler;
import it.proconsole.scheduler.model.Round;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.model.Sprint;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;

import java.util.List;
import java.util.stream.Collectors;

public class RoundRobinSprintsGenerator implements SprintsGenerator {
  private final DeveloperRepository developerRepository;
  private final Scheduler<Developer> scheduler;

  public RoundRobinSprintsGenerator(DeveloperRepository developerRepository, Scheduler<Developer> scheduler) {
    this.developerRepository = developerRepository;
    this.scheduler = scheduler;
  }

  @Override
  public List<Sprint> generateFor(Long teamId) {
    var developers = developerRepository.findByTeamId(teamId);
    var rounds = scheduler.scheduleFor(developers);
    return rounds.stream()
            .map(round -> new Sprint(round.id(), getPairsFor(round)))
            .collect(Collectors.toList());
  }

  private List<Pair> getPairsFor(Round<Developer> round) {
    return round.matches().stream()
            .map(match -> match.secondPlayer()
                    .map(it -> new Pair(List.of(match.firstPlayer(), it)))
                    .orElseGet(() -> new Pair(List.of(match.firstPlayer())))
            ).collect(Collectors.toList());
  }
}
