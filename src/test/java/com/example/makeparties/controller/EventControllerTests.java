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

import com.example.makeparties.entity.Event;
import com.example.makeparties.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(controllers = EventController.class)
@ExtendWith(MockitoExtension.class)
public class EventControllerTests {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private EventService eventService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void EventController_saveEvent() {
                Event event = Event.builder().title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null).build();

                when(eventService.saveEvent(event)).thenReturn(event);

                try {
                        mockMvc.perform(post("/events")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(event)))
                                        .andExpect(status().isOk());
                } catch (JsonProcessingException e) {
                        e.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        @Test
        public void EventController_fetchEventById() throws JsonProcessingException, Exception {
                Event event = Event.builder().eventId((long) 1).title("BBQ").desc("BYOB").imgUrl(null)
                                .takesPlaceOn(null)
                                .build();

                when(eventService.fetchEventById((long) 1)).thenReturn(event);

                mockMvc.perform(get("/events/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON))
                                // .content(objectMapper.writeValueAsString(event))) - could leave this?
                                .andExpect(status().isOk()) // can't really different request results with status codes
                                                            // bc I didn't use
                                                            // ResponseEntities for all routes
                                .andExpect(jsonPath("$.title").value("BBQ"));
        }

        @Test
        public void EventController_fetchEventList() throws Exception {
                Event event = Event.builder().eventId((long) 1).title("BBQ").desc("BYOB").imgUrl(null)
                                .takesPlaceOn(null)
                                .build();

                Event event2 = Event.builder().eventId((long) 2).title("Camping Trip").desc("Roughing it").imgUrl(null)
                                .takesPlaceOn(null).build();

                List<Event> list = Arrays.asList(new Event[] { event, event2 });

                when(eventService.fetchEventList()).thenReturn(list);

                mockMvc.perform(get("/events")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[1].title").value("Camping Trip"));
        }

        @Test
        public void EventController_deleteEvent() throws JsonProcessingException, Exception {

                Event event = Event.builder().eventId((long) 1).title("BBQ").desc("BYOB").imgUrl(null)
                                .takesPlaceOn(null)
                                .build();

                doNothing().when(eventService).deleteEventById(event.getEventId());

                mockMvc.perform(delete("/events/{id}", (long) 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(event)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.message").value("event 1 deleted"));
        }

        @Test
        public void EventController_updateEvent() throws JsonProcessingException, Exception {
                // Don't really need to save original event ?
                // Event event = Event.builder().eventId((long)
                // 1).title("BBQ").desc("BYOB").imgUrl(null).takesPlaceOn(null)
                // .build();

                Event event2 = Event.builder().eventId((long) 1).title("Camping Trip").desc("Roughing it").imgUrl(null)
                                .takesPlaceOn(null)
                                .build();

                Long eventId = (long) 1;
                when(eventService.updateEvent(eventId, event2)).thenReturn(event2);

                mockMvc.perform(put("/events/{id}", eventId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(event2)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.title").value("Camping Trip"));
        }

}
