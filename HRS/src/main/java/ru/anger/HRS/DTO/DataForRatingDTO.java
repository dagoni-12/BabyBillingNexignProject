package ru.anger.HRS.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataForRatingDTO {
    private String msisdn;
    private boolean partnerIsRomashkaSubscriber;
    private String callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate lastPaymentDate;
    private Integer tariffId;
    private Integer includedMinutes;
}

