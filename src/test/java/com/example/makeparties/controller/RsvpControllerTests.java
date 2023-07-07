package com.example.makeparties.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.makeparties.dto.RsvpDto;
import com.example.makeparties.service.RsvpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RsvpController.class)
@ExtendWith(MockitoExtension.class)
public class RsvpControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RsvpService rsvpService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void RsvpController_saveRsvp() throws JsonProcessingException, Exception {

        RsvpDto rsvpDto = RsvpDto.builder().rsvpId((long) 1).name("jim").email("jim@gmail.com").build();

        Long eventId = (long) 1;

        when(rsvpService.saveRsvp(eventId, rsvpDto)).thenReturn(rsvpDto);

        mockMvc.perform(post("/events/{id}/rsvps", eventId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rsvpDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("jim"));
    }

    @Test
    public void RsvpController_deleteRsvp() throws Exception {
        Long eventId = (long) 1;
        Long rsvpId = (long) 1;

        doNothing().when(rsvpService).deleteRsvpById(eventId, rsvpId);

        mockMvc.perform(delete("/events/{eventId}/rsvps/{rsvpId}", 1, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
