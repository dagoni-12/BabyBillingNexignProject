package ru.anger.BRT.DTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChargeResultDTO {
    private BigDecimal chargeAmount;
    private Integer minutesSpent;
    private boolean shouldUpdateLastPaymentDate;
}
