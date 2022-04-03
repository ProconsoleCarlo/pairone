package it.proconsole.utility.pairone.adapter.datastore.repository;

import it.proconsole.utility.pairone.adapter.datastore.exception.EntityNotSavedException;
import it.proconsole.utility.pairone.adapter.datastore.model.SprintEntity;
import it.proconsole.utility.pairone.adapter.datastore.repository.adapter.SprintAdapter;
import it.proconsole.utility.pairone.adapter.datastore.repository.crud.SprintEntityRepository;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.model.Sprint;
import it.proconsole.utility.pairone.core.repository.PairRepository;
import it.proconsole.utility.pairone.core.repository.SprintRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatastoreSprintRepositoryTest {
  private static final Long SPRINT_ID = 1L;
  private static final Long TEAM_ID = 9L;
  private static final Long ROUND = 1L;

  private static final List<Pair> PAIRS = List.of(new Pair(1L, Collections.emptyList()));
  private static final SprintEntity ENTITY_TO_SAVE = new SprintEntity(null, TEAM_ID, ROUND);
  private static final SprintEntity ENTITY_SAVED = new SprintEntity(SPRINT_ID, TEAM_ID, ROUND);
  private static final Sprint DOMAIN_TO_SAVE = new Sprint(ROUND, PAIRS);
  private static final Sprint DOMAIN_SAVED = new Sprint(SPRINT_ID, ROUND, PAIRS);

  @Mock
  private SprintEntityRepository sprintEntityRepository;
  @Mock
  private PairRepository pairRepository;
  @Mock
  private SprintAdapter sprintAdapter;

  private SprintRepository repository;

  @BeforeEach
  void setUp() {
    repository = new DatastoreSprintRepository(sprintEntityRepository, pairRepository, sprintAdapter);
  }

  @Test
  void deleteByTeamId() {
    when(sprintEntityRepository.findByTeamId(TEAM_ID)).thenReturn(List.of(ENTITY_SAVED));

    repository.deleteByTeamId(TEAM_ID);

    verify(sprintEntityRepository, times(1)).deleteByTeamId(TEAM_ID);
    verify(pairRepository, times(1)).deleteAllBySprintId(List.of(SPRINT_ID));
  }

  @Test
  void findByTeamId() {
    when(sprintEntityRepository.findByTeamId(TEAM_ID)).thenReturn(List.of(ENTITY_SAVED));
    when(pairRepository.findBySprintId(SPRINT_ID)).thenReturn(PAIRS);
    when(sprintAdapter.toDomain(ENTITY_SAVED, PAIRS)).thenReturn(DOMAIN_SAVED);

    var current = repository.findByTeamId(TEAM_ID);

    assertEquals(List.of(DOMAIN_SAVED), current);
  }

  @Test
  void saveAll() {
    when(sprintAdapter.fromDomain(DOMAIN_TO_SAVE, TEAM_ID)).thenReturn(ENTITY_TO_SAVE);
    when(sprintEntityRepository.save(ENTITY_TO_SAVE)).thenReturn(ENTITY_SAVED);
    when(pairRepository.saveAll(SPRINT_ID, PAIRS)).thenReturn(PAIRS);
    when(sprintAdapter.toDomain(ENTITY_SAVED, PAIRS)).thenReturn(DOMAIN_SAVED);

    var current = repository.saveAll(TEAM_ID, List.of(DOMAIN_TO_SAVE));

    assertEquals(List.of(DOMAIN_SAVED), current);
  }

  @Nested
  class Save {
    @Test
    void successfullySave() {
      when(sprintAdapter.fromDomain(DOMAIN_TO_SAVE, TEAM_ID)).thenReturn(ENTITY_TO_SAVE);
      when(sprintEntityRepository.save(ENTITY_TO_SAVE)).thenReturn(ENTITY_SAVED);
      when(pairRepository.saveAll(SPRINT_ID, PAIRS)).thenReturn(PAIRS);
      when(sprintAdapter.toDomain(ENTITY_SAVED, PAIRS)).thenReturn(DOMAIN_SAVED);

      var current = repository.save(TEAM_ID, DOMAIN_TO_SAVE);

      assertEquals(DOMAIN_SAVED, current);
    }

    @Test
    void somethingBadHappens() {
      when(sprintAdapter.fromDomain(DOMAIN_TO_SAVE, TEAM_ID)).thenReturn(ENTITY_TO_SAVE);
      when(sprintEntityRepository.save(ENTITY_TO_SAVE)).thenReturn(ENTITY_TO_SAVE);

      assertThrows(EntityNotSavedException.class, () -> repository.save(TEAM_ID, DOMAIN_TO_SAVE));

      verifyNoInteractions(pairRepository);
      verifyNoMoreInteractions(sprintAdapter);
    }
  }
}