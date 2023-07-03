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
    public RsvpDto saveRsvp(Long EventId, RsvpDto rsvpDto) {

        Rsvp rsvp = mapToEntity(rsvpDto);

        Event event = eventRepository.findById(EventId).orElseThrow();
     
        rsvp.setEvent(event);

        Rsvp completeRsvp = rsvpRepository.save(rsvp);

        return mapToDto(completeRsvp);
    }

    @Override
    public void deleteRsvpById(Long EventId, Long RsvpId) throws Exception {
        Event event = eventRepository.findById(EventId).orElseThrow();

        Rsvp rsvp = rsvpRepository.findById(RsvpId).orElseThrow();

        if(rsvp.getEvent().getEventId() != event.getEventId()) {
            throw new Exception("This rsvp does not belong to a Event");
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

    private Rsvp mapToEntity(RsvpDto RsvpDto) {
        Rsvp Rsvp = new Rsvp();
        Rsvp.setName(RsvpDto.getName());
        Rsvp.setEmail(RsvpDto.getEmail());
        return Rsvp;
    }
}