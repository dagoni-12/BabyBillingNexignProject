package ru.anger.HRS.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChargeResultDTO {
    private BigDecimal chargeAmount;
    private Integer minutesSpent;
    private boolean shouldUpdateLastPaymentDate;
}
