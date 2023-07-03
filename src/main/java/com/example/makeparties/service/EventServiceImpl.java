package com.example.makeparties.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.makeparties.entity.Event;
import com.example.makeparties.repository.EventRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event saveEvent(Event Event) {
        return eventRepository.save(Event);
    }

    @Override
    public List<Event> fetchEventList() {
        return eventRepository.findAll();
    }

    @Override
    public Event fetchEventById(Long EventId) throws EntityNotFoundException {
        Optional<Event> Event = eventRepository.findById(EventId);

        if (!Event.isPresent()) {
            throw new EntityNotFoundException("Event Not Available");
        }

        return Event.get();
    }

    @Override
    public void deleteEventById(Long EventId) {
        eventRepository.deleteById(EventId);
    }

    @Override
    public Event updateEvent(Long EventId, Event Event) {
        Event eventDB = eventRepository.findById(EventId).get();

        if (Objects.nonNull(Event.getTitle()) &&
                !"".equalsIgnoreCase(Event.getTitle())) {
            eventDB.setTitle(Event.getTitle());
        }

        if (Objects.nonNull(Event.getDesc()) &&
                !"".equalsIgnoreCase(Event.getDesc())) {
            eventDB.setDesc(Event.getDesc());
        }

        if (Objects.nonNull(Event.getImgUrl()) &&
                !"".equalsIgnoreCase(Event.getImgUrl())) {
            eventDB.setImgUrl(Event.getImgUrl());
        }

        // need to look into checking a date in java
        if (Objects.nonNull(Event.getTakesPlaceOn())) {
            eventDB.setTakesPlaceOn(Event.getTakesPlaceOn());
        }

        return eventRepository.save(eventDB);
    }

}