package com.example.makeparties.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.makeparties.entity.Event;
import com.example.makeparties.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTests {

    @Mock
    private EventRepository eventRepository;
    
    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    public void EventService_saveEvent(){
        Event event = Event.builder().title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

        when(eventRepository.save(Mockito.any(Event.class))).thenReturn(event);

        Event savedEvent = eventService.saveEvent(event);

        Assertions.assertThat(savedEvent.getTitle()).isEqualTo("BBQ");
    }

    @Test
    public void EventService_fetchEventList() {
        Event event = Event.builder().title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

        Event event2 = Event.builder().title("Camping Trip").desc("Roughing it").imgUrl(null).takesPlaceOn(null).build();

        List<Event> list = Arrays.asList(new Event[]{event, event2});

        when(eventRepository.findAll()).thenReturn(list);

        List<Event> savedEvents = eventService.fetchEventList();

        Assertions.assertThat(savedEvents).isNotNull();
        Assertions.assertThat(savedEvents.size()).isEqualTo(2);
    }

    @Test
    public void EventService_fetchEventById(){
        Long eventId = (long) 1;
        Event event = Event.builder().eventId(eventId).title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();
        when(eventRepository.findById(eventId)).thenReturn(Optional.ofNullable(event));

        Event eventReturn = eventService.fetchEventById(eventId);

        Assertions.assertThat(eventReturn).isNotNull();
        Assertions.assertThat(eventReturn.getTitle()).isEqualTo("BBQ");
    }

    /* 

    // didn't work although it is similar 

    @Test
    public void EventService_fetchEventById(){
        Event event = Event.builder().eventId((long) 1).title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

        Optional<Event> eventToFind = Optional.ofNullable(event); 

        when(eventRepository.findById((long) 1).thenReturn(eventToFind));

        Event savedEvent = eventService.fetchEventById((long) 1);

        Assertions.assertThat(savedEvent).isNotNull();
    }
    */

    @Test
    public void EventService_deleteEventById() {
        Long eventId = (long) 1;
        Event event = Event.builder().eventId(eventId).title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

        eventService.saveEvent(event);

        assertAll(() -> eventService.deleteEventById(eventId));
    }

    @Test
    public void EventService_updateEvent() {

        Long eventId = (long) 1;
        Event event = Event.builder().eventId(eventId).title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

        Event newEvent = Event.builder().eventId(eventId).title("BBQ").desc("Something different").imgUrl(null).takesPlaceOn(null).build();

        when(eventRepository.findById(eventId)).thenReturn(Optional.ofNullable(event));
        when(eventRepository.save(event)).thenReturn(event);

        Event updateReturn = eventService.updateEvent(eventId, newEvent);

        Assertions.assertThat(updateReturn).isNotNull();
        Assertions.assertThat(updateReturn.getDesc()).isEqualTo("Something different");
    }

}
