package it.proconsole.utility.pairone.rest.controller;

import it.proconsole.utility.pairone.core.Fixtures;
import it.proconsole.utility.pairone.core.model.Pair;
import it.proconsole.utility.pairone.core.repository.PairRepository;
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
@WebMvcTest(controllers = PairController.class)
class PairControllerTest {
  private static final Long SPRINT_ID = 123L;
  private static final String PAIRS_SAVED_JSON = "/pair/pairsSaved.json";
  private static final String PAIRS_TO_SAVE_JSON = "/pair/pairsToSave.json";

  @MockBean
  private PairRepository pairRepository;

  @Autowired
  private MockMvc mvc;

  @Test
  void getPairs() throws Exception {
    when(pairRepository.findBySprintId(SPRINT_ID))
            .thenReturn(Fixtures.readListFromClasspath(PAIRS_SAVED_JSON, Pair.class));

    mvc.perform(get("/team/{sprintId}/pair", SPRINT_ID))
            .andExpect(status().isOk())
            .andExpect(content().json(Fixtures.readFromClasspath(PAIRS_SAVED_JSON)));

    verify(pairRepository, times(1)).findBySprintId(SPRINT_ID);
    verifyNoMoreInteractions(pairRepository);
  }

  @Test
  void savePairs() throws Exception {
    when(pairRepository.saveAll(anyLong(), anyList()))
            .thenReturn(Fixtures.readListFromClasspath(PAIRS_SAVED_JSON, Pair.class));

    mvc.perform(post("/team/{sprintId}/pair", SPRINT_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Fixtures.readFromClasspath(PAIRS_TO_SAVE_JSON)))
            .andExpect(status().isOk())
            .andExpect(content().json(Fixtures.readFromClasspath(PAIRS_SAVED_JSON)));

    verify(pairRepository, times(1))
            .saveAll(SPRINT_ID, Fixtures.readListFromClasspath(PAIRS_TO_SAVE_JSON, Pair.class));
    verifyNoMoreInteractions(pairRepository);
  }
}