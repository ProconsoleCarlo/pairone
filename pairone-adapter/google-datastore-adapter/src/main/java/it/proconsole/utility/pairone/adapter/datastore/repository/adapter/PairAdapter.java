package it.proconsole.utility.pairone.adapter.datastore.repository.adapter;

import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.adapter.datastore.model.PairEntity;
import it.proconsole.utility.pairone.core.model.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class PairAdapter {
  private final DeveloperAdapter developerAdapter;

  public PairAdapter(DeveloperAdapter developerAdapter) {
    this.developerAdapter = developerAdapter;
  }

  public List<PairEntity> fromDomain(List<Pair> pairs, Long teamId) {
    return pairs.stream().map(it -> fromDomain(it, teamId)).collect(Collectors.toList());
  }

  private PairEntity fromDomain(Pair pair, Long teamId) {
    return new PairEntity(teamId, pair.firstId(), pair.secondId().orElse(null));
  }

  public Pair toDomain(PairEntity pair, List<DeveloperEntity> developers) {
    return new Pair(pair.getId(), developerAdapter.toDomain(developers));
  }
}
