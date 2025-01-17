package com.example.makeparties.service;

import com.example.makeparties.dto.RsvpDto;

public interface RsvpService {
    public RsvpDto saveRsvp(Long eventId, RsvpDto rsvpDto);

    public void deleteRsvpById(Long eventId, Long rsvpId) throws Exception;
}