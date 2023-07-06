package com.example.makeparties.repository;

import java.util.List;
//import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.makeparties.entity.Event;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EventRepositoryTests {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void EventRepository_save() {
        Event event = Event.builder().title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

        Event savedEvent = eventRepository.save(event);

        Assertions.assertThat(savedEvent).isNotNull();
    }

    @Test
    public void EventRepository_findAll() {
        Event event1 = Event.builder().title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

        Event event2 = Event.builder().title("Camping Trip").desc("Sleep under the stars").imgUrl(null)
                .takesPlaceOn(null).build();

        eventRepository.save(event1);

        eventRepository.save(event2);

        List<Event> eventList = eventRepository.findAll();

        Assertions.assertThat(eventList).isNotNull();

        Assertions.assertThat(eventList.size()).isEqualTo(2);
    }

    @Test
    public void EventRepository_findById() {
        Event event1 = Event.builder().title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

        Event event2 = Event.builder().title("Camping Trip").desc("Sleep under the stars").imgUrl(null)
                .takesPlaceOn(null).build();

        eventRepository.save(event1);

        eventRepository.save(event2);

        // could use Optional<Event> eventReturned =
        // eventRepository.findById(event2.getEventId());

        Event eventReturned = eventRepository.findById(event2.getEventId()).get();

        Assertions.assertThat(eventReturned).isNotNull();
        Assertions.assertThat(eventReturned.getTitle()).isEqualTo("Camping Trip");
        Assertions.assertThat(eventReturned.getDesc()).isEqualTo("Sleep under the stars");
        Assertions.assertThat(eventReturned.getImgUrl()).isNull();
        Assertions.assertThat(eventReturned.getTakesPlaceOn()).isNull();
    }

    @Test
    public void EventRepository_deleteById() {
        Event event1 = Event.builder().title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

        Event event2 = Event.builder().title("Camping Trip").desc("Sleep under the stars").imgUrl(null)
                .takesPlaceOn(null).build();

        eventRepository.save(event1);

        eventRepository.save(event2);

        eventRepository.deleteById(event2.getEventId());

        List<Event> eventList = eventRepository.findAll();

        Assertions.assertThat(eventList).isNotNull();

        Assertions.assertThat(eventList.size()).isEqualTo(1);
    }

    @Test
    public void EventRepository_update() {
        Event event1 = Event.builder().title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

        eventRepository.save(event1);

        Event eventReturned = eventRepository.findById(event1.getEventId()).get();

        eventReturned.setTitle("Camping Trip");
        eventReturned.setDesc("Sleep under the stars");

        Event updatedEvent = eventRepository.save(eventReturned);

        Assertions.assertThat(updatedEvent.getTitle()).isEqualTo("Camping Trip");

        Assertions.assertThat(updatedEvent.getDesc()).isEqualTo("Sleep under the stars");
    }

}
