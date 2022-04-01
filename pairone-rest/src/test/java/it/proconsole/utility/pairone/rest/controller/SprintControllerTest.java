package it.proconsole.utility.pairone.rest.controller;

import it.proconsole.utility.pairone.core.Fixtures;
import it.proconsole.utility.pairone.core.logic.SprintsGenerator;
import it.proconsole.utility.pairone.core.model.Sprint;
import it.proconsole.utility.pairone.core.repository.SprintRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = SprintController.class)
class SprintControllerTest {
  private static final Long TEAM_ID = 123L;
  private static final String SPRINTS_SAVED_JSON = "/sprint/sprintsSaved.json";
  private static final String SPRINTS_TO_SAVE_JSON = "/sprint/sprintsToSave.json";

  @MockBean
  private SprintRepository sprintRepository;
  @MockBean
  private SprintsGenerator sprintsGenerator;

  @Autowired
  private MockMvc mvc;

  @Test
  void getSprints() throws Exception {
    when(sprintRepository.findByTeamId(TEAM_ID))
            .thenReturn(Fixtures.readListFromClasspath(SPRINTS_SAVED_JSON, Sprint.class));

    mvc.perform(get("/team/{teamId}/sprint", TEAM_ID))
            .andExpect(status().isOk())
            .andExpect(content().json(Fixtures.readFromClasspath(SPRINTS_SAVED_JSON)));

    verify(sprintRepository, times(1)).findByTeamId(TEAM_ID);
    verifyNoMoreInteractions(sprintRepository);
  }

  @Test
  void createSprints() {
  }

  @Test
  void saveSprints() throws Exception {
    when(sprintRepository.saveAll(anyLong(), anyList()))
            .thenReturn(Fixtures.readListFromClasspath(SPRINTS_SAVED_JSON, Sprint.class));

    mvc.perform(post("/team/{teamId}/sprint", TEAM_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Fixtures.readFromClasspath(SPRINTS_TO_SAVE_JSON)))
            .andExpect(status().isOk())
            .andExpect(content().json(Fixtures.readFromClasspath(SPRINTS_SAVED_JSON)));

    verify(sprintRepository, times(1)).saveAll(TEAM_ID, Fixtures.readListFromClasspath(SPRINTS_TO_SAVE_JSON, Sprint.class));
    verifyNoMoreInteractions(sprintRepository);
  }
}