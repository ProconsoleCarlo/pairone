package it.proconsole.utility.pairone.core.repository;

import it.proconsole.utility.pairone.core.model.Sprint;

import java.util.List;

public interface SprintRepository {
  List<Sprint> findByTeamId(Long teamId);

  List<Sprint> saveAll(Long teamId, List<Sprint> sprints);
}
