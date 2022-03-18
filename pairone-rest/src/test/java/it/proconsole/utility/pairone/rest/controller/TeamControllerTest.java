package it.proconsole.utility.pairone.rest.controller;

import it.proconsole.utility.pairone.core.Fixtures;
import it.proconsole.utility.pairone.core.model.Team;
import it.proconsole.utility.pairone.core.repository.TeamRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = TeamController.class)
class TeamControllerTest {
  private static final Long TEAM_ID = 123L;
  private static final String TEAM_SAVED_JSON = "/team/teamSaved.json";
  private static final String TEAM_TO_SAVE_JSON = "/team/teamToSave.json";

  @MockBean
  private TeamRepository teamRepository;

  @Autowired
  private MockMvc mvc;

  @Nested
  class GetTeam {
    @Test
    void whenTeamExists() throws Exception {
      when(teamRepository.findById(TEAM_ID))
              .thenReturn(Optional.of(Fixtures.readFromClasspath(TEAM_SAVED_JSON, Team.class)));

      mvc.perform(get("/team/{teamId}", TEAM_ID))
              .andExpect(status().isOk())
              .andExpect(content().json(Fixtures.readFromClasspath(TEAM_SAVED_JSON)));

      verify(teamRepository, times(1)).findById(TEAM_ID);
      verifyNoMoreInteractions(teamRepository);
    }

    @Test
    void whenTeamIsNotExistent() throws Exception {
      when(teamRepository.findById(TEAM_ID)).thenReturn(Optional.empty());

      mvc.perform(get("/team/{teamId}", TEAM_ID))
              .andExpect(status().isNotFound());

      verify(teamRepository, times(1)).findById(TEAM_ID);
      verifyNoMoreInteractions(teamRepository);
    }
  }

  @Test
  void saveTeam() throws Exception {
    when(teamRepository.save(any(Team.class)))
            .thenReturn(Fixtures.readFromClasspath(TEAM_SAVED_JSON, Team.class));

    mvc.perform(post("/team")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Fixtures.readFromClasspath(TEAM_TO_SAVE_JSON)))
            .andExpect(status().isOk())
            .andExpect(content().json(Fixtures.readFromClasspath(TEAM_SAVED_JSON)));

    verify(teamRepository, times(1))
            .save(Fixtures.readFromClasspath(TEAM_TO_SAVE_JSON, Team.class));
    verifyNoMoreInteractions(teamRepository);
  }
}