package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.PairAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.SprintAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.SprintEntityRepository;
import it.proconsole.utility.pairone.core.model.Sprint;
import it.proconsole.utility.pairone.core.repository.PairRepository;
import it.proconsole.utility.pairone.core.repository.SprintRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatastoreSprintRepository implements SprintRepository {
  private final SprintEntityRepository entityRepository;
  private final PairRepository pairRepository;
  private final SprintAdapter sprintAdapter;
  private final PairAdapter pairAdapter;

  public DatastoreSprintRepository(
          SprintEntityRepository entityRepository,
          PairRepository pairRepository,
          SprintAdapter sprintAdapter,
          PairAdapter pairAdapter
  ) {
    this.entityRepository = entityRepository;
    this.pairRepository = pairRepository;
    this.sprintAdapter = sprintAdapter;
    this.pairAdapter = pairAdapter;
  }

  @Override
  public List<Sprint> findByTeamId(Long teamId) {
    return entityRepository.findByTeamId(teamId).stream()
            .map(entity -> {
              var sprintId = Optional.ofNullable(entity.getId()).orElseThrow(); //TODO
              var pairs = pairRepository.findBySprintId(sprintId);
              return sprintAdapter.toDomain(entity, pairs);
            }).collect(Collectors.toList());
  }

  @Override
  public List<Sprint> saveAll(Long teamId, List<Sprint> sprints) {
    return sprints.stream()
            .map(sprint -> save(teamId, sprint))
            .collect(Collectors.toList());
  }

  private Sprint save(Long teamId, Sprint sprint) {
    var sprintEntity = sprintAdapter.fromDomain(sprint, teamId);
    var savedEntity = entityRepository.save(sprintEntity);
    var savedPairs = Optional.ofNullable(savedEntity.getId())
            .map(sprintId -> pairRepository.saveAll(sprintId, sprint.pairs()))
            .orElseThrow();
    return sprintAdapter.toDomain(sprintEntity, savedPairs);
  }
}
