package it.proconsole.utility.pairone.core.repository;

import it.proconsole.utility.pairone.core.model.Team;

import java.util.List;

public interface TeamRepository {
  List<Team> findAll();

  List<Team> saveAll(List<Team> teams);
}
