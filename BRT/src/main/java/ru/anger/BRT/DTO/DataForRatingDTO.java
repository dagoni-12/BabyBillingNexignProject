package ru.anger.BRT.DTO;


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
    private int tariffId;
    private String callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
