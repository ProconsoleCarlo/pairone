package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.PairEntity;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class PairAdapter {
  public List<PairEntity> fromDomain(List<Pair> pairs, Long sprintId) {
    return pairs.stream().map(it -> fromDomain(it, sprintId)).collect(Collectors.toList());
  }

  private PairEntity fromDomain(Pair pair, Long sprintId) {
    return new PairEntity(pair.id(), sprintId, pair.firstId(), pair.secondId().orElse(null));
  }

  public Pair toDomain(PairEntity pair, List<Developer> developers) {
    return new Pair(pair.getId(), developers);
  }
}
