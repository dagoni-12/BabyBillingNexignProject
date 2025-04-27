package ru.anger.CDRGen.DTO;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {
    private String callType;
    private String subscriberMsisdn;
    private String partnerMsisdn;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
