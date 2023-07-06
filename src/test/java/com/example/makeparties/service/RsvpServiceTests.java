package com.example.makeparties.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.makeparties.dto.RsvpDto;
import com.example.makeparties.entity.Event;
import com.example.makeparties.entity.Rsvp;
import com.example.makeparties.repository.EventRepository;
import com.example.makeparties.repository.RsvpRepository;

@ExtendWith(MockitoExtension.class)
public class RsvpServiceTests {
    
    @Mock 
    private RsvpRepository rsvpRepository;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private RsvpServiceImpl rsvpService;

    @Test
    public void RsvpService_saveRsvp(){
        Event event = Event.builder().eventId((long) 1).title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();
        Rsvp rsvp = Rsvp.builder().rsvpId((long) 1).name("Jim").email(null).build();
        RsvpDto rsvpDto = RsvpDto.builder().rsvpId((long) 1).name("Jim").email("jim@gmail.com").build();

        when(eventRepository.findById(event.getEventId())).thenReturn(Optional.of(event));
        when(rsvpRepository.save(Mockito.any(Rsvp.class))).thenReturn(rsvp);

        RsvpDto savedRsvp = rsvpService.saveRsvp(event.getEventId(), rsvpDto);

        Assertions.assertThat(savedRsvp).isNotNull();

        Assertions.assertThat(savedRsvp.getName()).isEqualTo("Jim");
    }

    @Test
    public void RsvpService_deleteRsvpById() {
        Long eventId = (long) 1;
        Long rsvpId = (long) 1;

        Event event = Event.builder().eventId((long) 1).title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

        Rsvp rsvp = Rsvp.builder().rsvpId((long) 1).name("Jim").email(null).build();

        event.setRsvps(Arrays.asList(rsvp));
        rsvp.setEvent(event);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(rsvpRepository.findById(rsvpId)).thenReturn(Optional.of(rsvp));

        assertAll(() -> rsvpService.deleteRsvpById(eventId, rsvpId));
    }

}
