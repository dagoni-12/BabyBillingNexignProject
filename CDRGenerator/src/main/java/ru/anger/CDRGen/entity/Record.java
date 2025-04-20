package ru.anger.CDRGen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Record")
@Getter
@Setter
@NoArgsConstructor
public class Record{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String callType;

    private String caller;

    private String receiver;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endTime;

    public Record(String callType, String caller, String receiver, LocalDateTime startTime, LocalDateTime endTime) {
        this.callType = callType;
        this.caller = caller;
        this.receiver = receiver;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
