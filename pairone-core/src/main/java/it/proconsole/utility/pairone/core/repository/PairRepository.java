package it.proconsole.utility.pairone.core.repository;

import it.proconsole.utility.pairone.core.model.Pair;

import java.util.List;

public interface PairRepository {
  void deleteAllBySprintId(List<Long> sprintIds);

  List<Pair> findBySprintId(Long sprintId);

  List<Pair> saveAll(Long sprintId, List<Pair> pairs);
}
