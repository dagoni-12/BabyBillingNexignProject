package ru.anger.BRT.DTO;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {
    private String callType;
    private String caller;
    private String receiver;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
