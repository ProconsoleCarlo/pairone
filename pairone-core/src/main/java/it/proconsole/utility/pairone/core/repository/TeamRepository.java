package it.proconsole.utility.pairone.core.repository;

import it.proconsole.utility.pairone.core.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {
  List<Team> findAll();

  Optional<Team> findById(Long id);

  Team save(Team team);
}
