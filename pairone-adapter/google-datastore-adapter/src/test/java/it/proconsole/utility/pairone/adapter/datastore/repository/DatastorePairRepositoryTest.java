package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.model.DeveloperEntity;
import it.proconsole.utility.pairone.adapter.datastore.model.PairEntity;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.PairAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.DeveloperEntityRepository;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.PairEntityRepository;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.repository.PairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatastorePairRepositoryTest {
  private static final Long TEAM_ID = 987L;
  private static final Long SPRINT_ID = 9L;
  private static final Long PAIR_ID = 123L;
  private static final Long ANOTHER_PAIR_ID = 456L;
  private static final Long A_DEVELOPER_ID = 1L;
  private static final Long ANOTHER_DEVELOPER_ID = 2L;
  private static final List<DeveloperEntity> DEVELOPER_ENTITIES = List.of(
          new DeveloperEntity(A_DEVELOPER_ID, "Dev1", TEAM_ID),
          new DeveloperEntity(ANOTHER_DEVELOPER_ID, "Dev2", TEAM_ID)
  );
  private static final List<Developer> DOMAIN_DEVELOPERS = List.of(
          new Developer(A_DEVELOPER_ID, "Dev1"),
          new Developer(ANOTHER_DEVELOPER_ID, "Dev2")
  );
  private static final PairEntity A_PAIR_ENTITY = new PairEntity(PAIR_ID, SPRINT_ID, A_DEVELOPER_ID, ANOTHER_DEVELOPER_ID);
  private static final PairEntity ANOTHER_PAIR_ENTITY = new PairEntity(ANOTHER_PAIR_ID, SPRINT_ID, ANOTHER_DEVELOPER_ID, A_DEVELOPER_ID);
  private static final List<PairEntity> PAIR_ENTITIES = List.of(A_PAIR_ENTITY, ANOTHER_PAIR_ENTITY);
  private static final Pair A_DOMAIN_PAIR = new Pair(PAIR_ID, DOMAIN_DEVELOPERS);
  private static final Pair ANOTHER_DOMAIN_PAIR = new Pair(ANOTHER_PAIR_ID, DOMAIN_DEVELOPERS);
  private static final List<Pair> DOMAIN_PAIRS = List.of(A_DOMAIN_PAIR, ANOTHER_DOMAIN_PAIR);

  @Mock
  private PairEntityRepository pairEntityRepository;
  @Mock
  private DeveloperEntityRepository developerEntityRepository;
  @Mock
  private PairAdapter pairAdapter;

  private PairRepository repository;

  @BeforeEach
  void setUp() {
    repository = new DatastorePairRepository(pairEntityRepository, developerEntityRepository, pairAdapter);
  }

  @Test
  void findBySprintId() {
    when(pairEntityRepository.findBySprintId(SPRINT_ID)).thenReturn(PAIR_ENTITIES);
    when(developerEntityRepository.findAllById(A_PAIR_ENTITY.developerIds())).thenReturn(DEVELOPER_ENTITIES);
    when(developerEntityRepository.findAllById(ANOTHER_PAIR_ENTITY.developerIds())).thenReturn(DEVELOPER_ENTITIES);
    when(pairAdapter.toDomain(A_PAIR_ENTITY, DEVELOPER_ENTITIES)).thenReturn(A_DOMAIN_PAIR);
    when(pairAdapter.toDomain(ANOTHER_PAIR_ENTITY, DEVELOPER_ENTITIES)).thenReturn(ANOTHER_DOMAIN_PAIR);

    var current = repository.findBySprintId(SPRINT_ID);

    assertEquals(DOMAIN_PAIRS, current);
  }

  @Test
  void saveAll() {
    when(pairAdapter.fromDomain(DOMAIN_PAIRS, SPRINT_ID)).thenReturn(PAIR_ENTITIES);
    when(pairEntityRepository.findBySprintId(SPRINT_ID)).thenReturn(PAIR_ENTITIES);
    when(developerEntityRepository.findAllById(A_PAIR_ENTITY.developerIds())).thenReturn(DEVELOPER_ENTITIES);
    when(developerEntityRepository.findAllById(ANOTHER_PAIR_ENTITY.developerIds())).thenReturn(DEVELOPER_ENTITIES);
    when(pairAdapter.toDomain(A_PAIR_ENTITY, DEVELOPER_ENTITIES)).thenReturn(A_DOMAIN_PAIR);
    when(pairAdapter.toDomain(ANOTHER_PAIR_ENTITY, DEVELOPER_ENTITIES)).thenReturn(ANOTHER_DOMAIN_PAIR);

    var current = repository.saveAll(SPRINT_ID, DOMAIN_PAIRS);

    assertEquals(DOMAIN_PAIRS, current);
    verify(pairEntityRepository, times(1)).saveAll(PAIR_ENTITIES);
  }
}