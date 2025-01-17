package com.example.makeparties.service;

import java.util.List;

import com.example.makeparties.entity.Event;

import jakarta.persistence.EntityNotFoundException;

public interface EventService {
    public Event saveEvent(Event event);

    public List<Event> fetchEventList();

    public Event fetchEventById(Long eventId) throws EntityNotFoundException;

    public void deleteEventById(Long eventId);

    public Event updateEvent(Long eventId, Event event);
}
