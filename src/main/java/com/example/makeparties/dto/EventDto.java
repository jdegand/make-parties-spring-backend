package com.example.makeparties.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private Long eventId;

    private String title;
    private String desc;
    private String imgUrl;
    private Date takesPlaceOn;

}
