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
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> fetchEventList() {
        return eventRepository.findAll();
    }

    @Override
    public Event fetchEventById(Long eventId) throws EntityNotFoundException {
        Optional<Event> event = eventRepository.findById(eventId);

        if (!event.isPresent()) {
            throw new EntityNotFoundException("Event Not Available");
        }

        return event.get();
    }

    @Override
    public void deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public Event updateEvent(Long eventId, Event event) {
        Event eventDB = eventRepository.findById(eventId).get();

        if (Objects.nonNull(event.getTitle()) &&
                !"".equalsIgnoreCase(event.getTitle())) {
            eventDB.setTitle(event.getTitle());
        }

        if (Objects.nonNull(event.getDesc()) &&
                !"".equalsIgnoreCase(event.getDesc())) {
            eventDB.setDesc(event.getDesc());
        }

        if (Objects.nonNull(event.getImgUrl()) &&
                !"".equalsIgnoreCase(event.getImgUrl())) {
            eventDB.setImgUrl(event.getImgUrl());
        }

        // need to look into checking a date in java
        if (Objects.nonNull(event.getTakesPlaceOn())) {
            eventDB.setTakesPlaceOn(event.getTakesPlaceOn());
        }

        return eventRepository.save(eventDB);
    }

}