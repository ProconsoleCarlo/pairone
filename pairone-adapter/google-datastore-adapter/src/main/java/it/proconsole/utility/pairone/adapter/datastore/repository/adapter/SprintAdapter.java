package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.SprintEntity;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.model.Sprint;

import java.util.List;

public class SprintAdapter {
  public List<SprintEntity> fromDomain(List<Sprint> sprints, Long teamId) {
    return sprints.stream()
            .map(it -> fromDomain(it, teamId))
            .toList();
  }

  public SprintEntity fromDomain(Sprint sprint, Long teamId) {
    return new SprintEntity(sprint.id(), teamId, sprint.round());
  }

  public Sprint toDomain(SprintEntity sprint, List<Pair> pairs) {
    return new Sprint(sprint.getId(), sprint.getRound(), pairs);
  }
}
