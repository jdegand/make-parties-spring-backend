package com.example.makeparties.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.makeparties.entity.Rsvp;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RsvpRepositoryTests {

    @Autowired
    private RsvpRepository rsvpRepository;

    @Test
    public void rsvpRepository_save() {
        Rsvp rsvp = Rsvp.builder().name("Jim").email(null).build();

        Rsvp savedRsvp = rsvpRepository.save(rsvp);

        Assertions.assertThat(savedRsvp).isNotNull();
    }

    @Test
    public void rsvpRepository_findById() {
        Rsvp rsvp1 = Rsvp.builder().name("Kate").email("kate@gmail.com").build();

        Rsvp rsvp2 = Rsvp.builder().name("Sam").email("sam@gmail.com").build();

        rsvpRepository.save(rsvp1);

        rsvpRepository.save(rsvp2);
        
        Rsvp rsvpReturned = rsvpRepository.findById(rsvp2.getRsvpId()).get();

        Assertions.assertThat(rsvpReturned).isNotNull();
        Assertions.assertThat(rsvpReturned.getName()).isEqualTo("Sam");
        Assertions.assertThat(rsvpReturned.getEmail()).isEqualTo("sam@gmail.com");
    }

    @Test
    public void rsvpRepository_deleteById() {
        Rsvp rsvp1 = Rsvp.builder().name("Kate").email("kate@gmail.com").build();

        Rsvp rsvp2 = Rsvp.builder().name("Sam").email("sam@gmail.com").build();

        rsvpRepository.save(rsvp1);

        rsvpRepository.save(rsvp2);

        rsvpRepository.deleteById(rsvp2.getRsvpId());

        List<Rsvp> rsvpList = rsvpRepository.findAll();

        Assertions.assertThat(rsvpList).isNotNull();

        Assertions.assertThat(rsvpList.size()).isEqualTo(1);
    }

}

