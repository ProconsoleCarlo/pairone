package it.proconsole.utility.pairone.rest.controller;

import it.proconsole.utility.pairone.core.Fixtures;
import it.proconsole.utility.pairone.core.model.Developer;
import it.proconsole.utility.pairone.core.repository.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = DeveloperController.class)
class DeveloperControllerTest {
  private static final String DEVELOPERS_SAVED_JSON = "/developer/developersSaved.json";
  private static final String DEVELOPERS_TO_SAVE_JSON = "/developer/developersToSave.json";

  @MockBean
  private DeveloperRepository developerRepository;

  @Autowired
  private MockMvc mvc;

  @Test
  void getDevelopers() throws Exception {
    when(developerRepository.findAll())
            .thenReturn(Fixtures.readListFromClasspath(DEVELOPERS_SAVED_JSON, Developer.class));

    mvc.perform(get("/developer"))
            .andExpect(status().isOk())
            .andExpect(content().json(Fixtures.readFromClasspath(DEVELOPERS_SAVED_JSON)));

    verify(developerRepository, times(1)).findAll();
    verifyNoMoreInteractions(developerRepository);
  }

  @Test
  void saveDevelopers() throws Exception {
    when(developerRepository.saveAll(anyList()))
            .thenReturn(Fixtures.readListFromClasspath(DEVELOPERS_SAVED_JSON, Developer.class));

    mvc.perform(post("/developer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Fixtures.readFromClasspath(DEVELOPERS_TO_SAVE_JSON)))
            .andExpect(status().isOk())
            .andExpect(content().json(Fixtures.readFromClasspath(DEVELOPERS_SAVED_JSON)));

    verify(developerRepository, times(1))
            .saveAll(Fixtures.readListFromClasspath(DEVELOPERS_TO_SAVE_JSON, Developer.class));
    verifyNoMoreInteractions(developerRepository);
  }
}