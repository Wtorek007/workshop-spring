package com.pivovarit.movies.domain;

import com.pivovarit.movies.api.MovieDto;
import com.pivovarit.movies.api.MovieTypeDto;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class RentalIntegrationMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieFacade movieFacade;

    @Test
    public void example_1() throws Exception {
        Mockito
          .when(movieFacade.findMovie(Mockito.any()))
          .thenReturn(Optional.of(new MovieDto(42L, "foo", new MovieTypeDto("NEW"), "some-details")));

        mockMvc.perform(
          get("/movies/v1/42"))
          .andExpect(status().is2xxSuccessful())
          .andExpect(jsonPath("$.id", is(42)))
          .andExpect(jsonPath("$.title", is("foo")))
          .andExpect(jsonPath("$.details", is("some-details")))
          .andExpect(jsonPath("$.type.movieType", is("NEW")));
    }
}
