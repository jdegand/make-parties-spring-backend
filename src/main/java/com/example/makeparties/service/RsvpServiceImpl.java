package com.example.makeparties.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.makeparties.dto.RsvpDto;
import com.example.makeparties.entity.Event;
import com.example.makeparties.entity.Rsvp;
import com.example.makeparties.repository.EventRepository;
import com.example.makeparties.repository.RsvpRepository;

@Service
public class RsvpServiceImpl implements RsvpService {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private RsvpRepository rsvpRepository;

    @Override
    public RsvpDto saveRsvp(Long eventId, RsvpDto rsvpDto) {
        Rsvp rsvp = mapToEntity(rsvpDto);
        Event event = eventRepository.findById(eventId).orElseThrow();
        rsvp.setEvent(event);
        Rsvp completeRsvp = rsvpRepository.save(rsvp);
        return mapToDto(completeRsvp);
    }

    @Override
    public void deleteRsvpById(Long eventId, Long rsvpId) throws Exception {
        Event event = eventRepository.findById(eventId).orElseThrow();
        Rsvp rsvp = rsvpRepository.findById(rsvpId).orElseThrow();

        if(rsvp.getEvent().getEventId() != event.getEventId()) {
            throw new Exception("This rsvp does not belong to a event");
        }

        rsvpRepository.delete(rsvp);
    }

    private RsvpDto mapToDto(Rsvp rsvp) {
        RsvpDto rsvpDto = new RsvpDto();
        rsvpDto.setRsvpId(rsvp.getRsvpId());
        rsvpDto.setName(rsvp.getName());
        rsvpDto.setEmail(rsvp.getEmail());
        return rsvpDto;
    }

    private Rsvp mapToEntity(RsvpDto rsvpDto) {
        Rsvp rsvp = new Rsvp();
        rsvp.setName(rsvpDto.getName());
        rsvp.setEmail(rsvpDto.getEmail());
        return rsvp;
    }
}