package ru.anger.BRT.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "romashka_calls_info")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CallInfo {
    @Id
    @Column(name = "romashka_call_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "call_type")
    private String callType;

    @Column(name = "subscriber_msisdn")
    private String subscriberMsisdn;

    @Column(name = "partner_msisdn")
    private String partnerMsisdn;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public CallInfo(String callType, String subscriberMsisdn, String partnerMsisdn, LocalDateTime startTime, LocalDateTime endTime) {
        this.callType = callType;
        this.subscriberMsisdn = subscriberMsisdn;
        this.partnerMsisdn = partnerMsisdn;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
