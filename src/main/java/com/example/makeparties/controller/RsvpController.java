package com.example.makeparties.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.makeparties.service.RsvpService;
import com.example.makeparties.dto.RsvpDto;

@RestController
public class RsvpController {
 
    @Autowired
    private RsvpService rsvpService;

    @PostMapping("/events/{id}/rsvps") 
    public RsvpDto saveRsvp(@PathVariable("id") Long eventId, @RequestBody RsvpDto rsvpDto) {
        return rsvpService.saveRsvp(eventId, rsvpDto);
    }

    @DeleteMapping("/events/{eventId}/rsvps/{rsvpId}")
    public void deleteRsvp(@PathVariable("eventId") Long eventId, @PathVariable("rsvpId") Long rsvpId) throws Exception {
        rsvpService.deleteRsvpById(eventId, rsvpId);
    }
}
