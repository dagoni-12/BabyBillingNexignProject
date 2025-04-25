package ru.anger.HRS.result;

import org.springframework.stereotype.Component;
import ru.anger.HRS.DTO.ChargeResultDTO;

import java.math.BigDecimal;

@Component
public class ResultBuilder {

    public ChargeResultDTO build(String msisdn, BigDecimal amount) {
        ChargeResultDTO dto = new ChargeResultDTO();
        dto.setMsisdn(msisdn);
        dto.setChargeAmount(amount);
        return dto;
    }
}

