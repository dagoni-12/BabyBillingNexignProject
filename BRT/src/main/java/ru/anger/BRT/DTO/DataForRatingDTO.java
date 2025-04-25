package ru.anger.BRT.DTO;


import lombok.*;

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
    private Integer tariffId;
}
