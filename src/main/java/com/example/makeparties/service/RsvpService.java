package com.example.makeparties.service;

import com.example.makeparties.dto.RsvpDto;

public interface RsvpService {
    public RsvpDto saveRsvp(Long EventId, RsvpDto rsvpDto);

    public void deleteRsvpById(Long EventId, Long RsvpId) throws Exception;
}