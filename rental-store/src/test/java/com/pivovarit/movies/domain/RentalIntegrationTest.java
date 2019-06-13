package com.pivovarit.movies.domain;

import com.pivovarit.movies.api.MovieDto;
import com.pivovarit.movies.api.MovieTypeDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RentalIntegrationTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("@BeforeClass");
    }

    @Before
    public void before() {
        System.out.println("@Before");
    }

    @Autowired
    private MovieFacade movieFacade;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void foo() throws Exception {
        MovieDto result = testRestTemplate
          .getForObject("http://localhost:" + port + "/movies/v1/42", MovieDto.class);

        Assertions.assertThat(result.getId()).isEqualTo(42L);
    }

    @Test
    public void addMovieTest() throws Exception {
        testRestTemplate.postForObject("http://localhost:" + port + "/movies", new MovieDto(1L, "1", new MovieTypeDto("NEW"), "dets"), Void.class);
        MovieDto result = testRestTemplate.getForObject("http://localhost:" + port + "/movies/v1/1", MovieDto.class);


        Assertions.assertThat(result.getId()).isEqualTo(1L);
        Assertions.assertThat(result.getTitle()).isEqualTo("1");
    }

    @Test
    public void foo2() throws Exception {
    }
}
