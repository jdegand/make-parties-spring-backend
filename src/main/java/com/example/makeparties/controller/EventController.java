package com.example.makeparties.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.makeparties.dto.DeleteResponse;
import com.example.makeparties.entity.Event;
import com.example.makeparties.service.EventService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/events")
    public Event saveEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    @GetMapping("/events")
    public List<Event> fetchEventList() {
        return eventService.fetchEventList();
    }

    @GetMapping("/events/{id}")
    public Event fetcheventById(@PathVariable("id") Long eventId)
            throws EntityNotFoundException {
        return eventService.fetchEventById(eventId);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<DeleteResponse> deleteEventById(@PathVariable("id") Long eventId) {
        eventService.deleteEventById(eventId);
        String res = "event " + eventId + " deleted";
        DeleteResponse data = DeleteResponse.builder().message(res).build();
        return ResponseEntity.ok(data);
    }

    @PutMapping("/events/{id}")
    public Event updateEvent(@PathVariable("id") Long eventId, @RequestBody Event event) {
        return eventService.updateEvent(eventId, event);
    }

}