package ru.anger.HRS.DTO;

// src/main/java/com/example/hrs/dto/CallForRatingDTO.java

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataForRatingDTO {
    private String msisdn;
    private boolean partnerIsRomashkaSubscriber;
    private String callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer tariffId;
}

