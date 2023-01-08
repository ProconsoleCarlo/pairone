package it.proconsole.utility.pairone.core.logic;

import it.proconsole.scheduler.logic.Scheduler;
import it.proconsole.scheduler.model.Round;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.model.Sprint;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import it.proconsole.utility.pairone.core.repository.SprintRepository;

import java.util.List;
import java.util.Optional;

public class RoundRobinSprintsGenerator implements SprintsGenerator {
  private final DeveloperRepository developerRepository;
  private final SprintRepository sprintRepository;
  private final Scheduler<Developer> scheduler;

  public RoundRobinSprintsGenerator(
          DeveloperRepository developerRepository,
          SprintRepository sprintRepository,
          Scheduler<Developer> scheduler
  ) {
    this.developerRepository = developerRepository;
    this.sprintRepository = sprintRepository;
    this.scheduler = scheduler;
  }

  @Override
  public List<Sprint> generateFor(Long teamId) {
    var developers = developerRepository.findByTeamId(teamId);
    var rounds = scheduler.scheduleFor(developers);
    var sprints = rounds.stream()
            .map(round -> new Sprint(round.id(), getPairsFor(round)))
            .toList();
    sprintRepository.deleteByTeamId(teamId);
    return sprintRepository.saveAll(teamId, sprints);
  }

  private List<Pair> getPairsFor(Round<Developer> round) {
    return round.matches().stream()
            .map(match -> Optional.ofNullable(match.secondPlayer())
                    .map(it -> new Pair(List.of(match.firstPlayer(), it)))
                    .orElseGet(() -> new Pair(List.of(match.firstPlayer())))
            ).toList();
  }
}
