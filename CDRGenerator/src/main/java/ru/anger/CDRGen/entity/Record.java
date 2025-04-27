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
    private Long id;

    private String callType;

    private String subscriberMsisdn;

    private String partnerMsisdn;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public Record(String callType, String subscriberMsisdn, String partnerMsisdn, LocalDateTime startTime, LocalDateTime endTime) {
        this.callType = callType;
        this.subscriberMsisdn = subscriberMsisdn;
        this.partnerMsisdn = partnerMsisdn;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
