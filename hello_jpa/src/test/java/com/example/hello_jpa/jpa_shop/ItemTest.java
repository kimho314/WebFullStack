package com.example.hello_jpa.jpa_shop;

import com.example.hello_jpa.jpa_shop.domain.Movie;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@DataJpaTest
public class ItemTest {

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    @Commit
    void saveMovieTest() {
        Movie movie = new Movie();
        movie.setDirector("aaaa");
        movie.setActor("bbbb");
        movie.setName("name");
        movie.setPrice(10000);
        movie.setStockQuantity(20000);

        em.persist(movie);
        em.flush();
        em.clear();

        Movie foundMovie = em.find(Movie.class, movie.getId());
        log.info("foundMovie = " + foundMovie.toString());
    }
}
