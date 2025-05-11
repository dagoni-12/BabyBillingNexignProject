package ru.anger.BRT.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberInfoResponse {
    private String fullName;
    private String msisdn;
    private BigDecimal balance;
    private int tariffId;
}