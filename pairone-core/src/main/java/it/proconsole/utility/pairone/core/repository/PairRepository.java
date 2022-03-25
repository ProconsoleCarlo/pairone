package it.proconsole.utility.pairone.core.repository;

import it.proconsole.utility.pairone.core.model.Pair;

import java.util.List;

public interface PairRepository {
  List<Pair> findByTeamId(Long teamId);

  List<Pair> saveAll(Long teamId, List<Pair> pairs);
}
