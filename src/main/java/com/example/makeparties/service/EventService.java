package com.example.makeparties.service;

import java.util.List;

import com.example.makeparties.entity.Event;

import jakarta.persistence.EntityNotFoundException;

public interface EventService {
    public Event saveEvent(Event Event);

    public List<Event> fetchEventList();

    public Event fetchEventById(Long EventId) throws EntityNotFoundException;

    public void deleteEventById(Long EventId);

    public Event updateEvent(Long EventId, Event Event);
}
