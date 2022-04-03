package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.PairAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.PairEntityRepository;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import it.proconsole.utility.pairone.core.repository.PairRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DatastorePairRepository implements PairRepository {
  private final PairEntityRepository entityRepository;
  private final DeveloperRepository developerRepository;
  private final PairAdapter pairAdapter;

  public DatastorePairRepository(
          PairEntityRepository entityRepository,
          DeveloperRepository developerRepository,
          PairAdapter pairAdapter
  ) {
    this.entityRepository = entityRepository;
    this.developerRepository = developerRepository;
    this.pairAdapter = pairAdapter;
  }

  @Override
  public void deleteAllBySprintId(List<Long> sprintIds) {
    entityRepository.deleteAllBySprintId(sprintIds);
  }

  @Override
  public List<Pair> findBySprintId(Long sprintId) {
    return entityRepository.findBySprintId(sprintId).stream()
            .map(entity -> {
              var developers = developerRepository.findAllById(entity.developerIds());
              return pairAdapter.toDomain(entity, developers);
            }).collect(Collectors.toList());
  }

  @Override
  public List<Pair> saveAll(Long sprintId, List<Pair> pairs) {
    return entityRepository.saveAll(pairAdapter.fromDomain(pairs, sprintId)).stream()
            .map(entity -> {
              var developers = pairs.stream()
                      .flatMap(it -> it.members().stream())
                      .filter(it -> entity.developerIds().contains(it.id()))
                      .distinct()
                      .collect(Collectors.toList());
              return pairAdapter.toDomain(entity, developers);
            }).collect(Collectors.toList());
  }
}
