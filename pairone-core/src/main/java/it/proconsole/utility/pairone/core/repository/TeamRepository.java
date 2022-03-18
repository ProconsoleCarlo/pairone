package it.proconsole.utility.pairone.core.repository;

import it.proconsole.utility.pairone.core.model.Team;

import java.util.Optional;

public interface TeamRepository {
  Optional<Team> findById(Long id);

  Team save(Team team);
}
