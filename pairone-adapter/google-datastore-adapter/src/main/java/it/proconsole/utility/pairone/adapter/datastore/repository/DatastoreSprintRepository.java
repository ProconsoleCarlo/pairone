package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.exception.EntityNotSavedException;
import it.proconsole.utility.pairone.adapter.datastore.model.SprintEntity;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.SprintAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.SprintEntityRepository;
import it.proconsole.utility.pairone.core.model.Sprint;
import it.proconsole.utility.pairone.core.repository.PairRepository;
import it.proconsole.utility.pairone.core.repository.SprintRepository;

import java.util.List;
import java.util.Optional;

public class DatastoreSprintRepository implements SprintRepository {
  private final SprintEntityRepository entityRepository;
  private final PairRepository pairRepository;
  private final SprintAdapter sprintAdapter;

  public DatastoreSprintRepository(
          SprintEntityRepository entityRepository,
          PairRepository pairRepository,
          SprintAdapter sprintAdapter
  ) {
    this.entityRepository = entityRepository;
    this.pairRepository = pairRepository;
    this.sprintAdapter = sprintAdapter;
  }

  @Override
  public void deleteByTeamId(Long teamId) {
    var existingSprints = entityRepository.findByTeamId(teamId);
    pairRepository.deleteAllBySprintId(existingSprints.stream().map(SprintEntity::getId).toList());
    entityRepository.deleteByTeamId(teamId);
  }

  @Override
  public List<Sprint> findByTeamId(Long teamId) {
    return entityRepository.findByTeamId(teamId).stream()
            .map(sprint -> Optional.ofNullable(sprint.getId())
                    .map(sprintId -> sprintAdapter.toDomain(sprint, pairRepository.findBySprintId(sprintId)))
                    .orElseThrow(() -> EntityNotSavedException.forSprint(sprint)))
            .toList();
  }

  @Override
  public List<Sprint> saveAll(Long teamId, List<Sprint> sprints) {
    return sprints.stream()
            .map(sprint -> save(teamId, sprint))
            .toList();
  }

  @Override
  public Sprint save(Long teamId, Sprint sprint) {
    var sprintEntity = sprintAdapter.fromDomain(sprint, teamId);
    var savedEntity = entityRepository.save(sprintEntity);
    var savedPairs = Optional.ofNullable(savedEntity.getId())
            .map(sprintId -> pairRepository.saveAll(sprintId, sprint.pairs()))
            .orElseThrow(EntityNotSavedException::forSprint);
    return sprintAdapter.toDomain(savedEntity, savedPairs);
  }
}
