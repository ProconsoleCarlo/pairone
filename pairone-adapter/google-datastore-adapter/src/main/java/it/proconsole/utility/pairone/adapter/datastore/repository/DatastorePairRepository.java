package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.PairAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.DeveloperEntityRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.PairEntityRepository;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.repository.PairRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DatastorePairRepository implements PairRepository {
  private final PairEntityRepository entityRepository;
  private final DeveloperEntityRepository developerEntityRepository;
  private final PairAdapter pairAdapter;

  public DatastorePairRepository(
          PairEntityRepository entityRepository,
          DeveloperEntityRepository developerEntityRepository,
          PairAdapter pairAdapter
  ) {
    this.entityRepository = entityRepository;
    this.developerEntityRepository = developerEntityRepository;
    this.pairAdapter = pairAdapter;
  }

  @Override
  public List<Pair> findByTeamId(Long teamId) {
    return entityRepository.findByTeamId(teamId).stream()
            .map(entity -> {
              var developers = developerEntityRepository.findAllById(entity.developerIds());
              return pairAdapter.toDomain(entity, developers);
            }).collect(Collectors.toList());
  }

  @Override
  public List<Pair> saveAll(Long teamId, List<Pair> pairs) {
    entityRepository.saveAll(pairAdapter.fromDomain(pairs, teamId));
    return findByTeamId(teamId);
  }
}
