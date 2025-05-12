package ru.anger.HRS.calculation;

import org.springframework.stereotype.Component;
import ru.anger.HRS.DTO.ChargeResultDTO;
import ru.anger.HRS.DTO.DataForRatingDTO;
import ru.anger.HRS.tariff.TariffData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
public class CalculationEngine {

    public ChargeResultDTO calculate(DataForRatingDTO dto, TariffData tariffData) {
        Duration callDuration = Duration.between(dto.getStartTime(), dto.getEndTime());
        long seconds = callDuration.getSeconds();
        int minutesOfCall = (int) ((seconds + 59) / 60);

        BigDecimal totalCharge = BigDecimal.ZERO;
        boolean shouldUpdateLastPaymentDate = false;
        int minutesSpent = 0;

        if (tariffData.isMonthly()) {
            long daysSinceLastPayment = ChronoUnit.DAYS.between(dto.getLastPaymentDate(), dto.getStartTime().toLocalDate());

            if (daysSinceLastPayment >= 30) {
                totalCharge = totalCharge.add(tariffData.getBaseValue());
                shouldUpdateLastPaymentDate = true;
            }

            if (dto.getIncludedMinutes() != null && dto.getIncludedMinutes() > 0) {
                if (dto.getIncludedMinutes() >= minutesOfCall) {
                    minutesSpent = minutesOfCall;
                } else {
                    minutesSpent = dto.getIncludedMinutes();
                    int overageMinutes = minutesOfCall - dto.getIncludedMinutes();
                    BigDecimal overage = tariffData.getRate().multiply(BigDecimal.valueOf(overageMinutes));
                    overage = overage.divide(new BigDecimal("0.1"), 0, RoundingMode.UP)
                            .multiply(new BigDecimal("0.1"));
                    totalCharge = totalCharge.add(overage);
                }
            }
        } else {
            BigDecimal classicCharge = tariffData.getRate().multiply(BigDecimal.valueOf(minutesOfCall));
            classicCharge = classicCharge.divide(new BigDecimal("0.1"), 0, RoundingMode.UP)
                    .multiply(new BigDecimal("0.1"));
            totalCharge = totalCharge.add(classicCharge);
        }
        return new ChargeResultDTO(totalCharge, minutesSpent, shouldUpdateLastPaymentDate);
    }
}
