package ru.anger.HRS.calculation;

import org.springframework.stereotype.Component;
import ru.anger.HRS.DTO.DataForRatingDTO;
import ru.anger.HRS.tariff.TariffData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CalculationEngine {

    public BigDecimal calculate(DataForRatingDTO dto, TariffData data) {
        long seconds = Duration.between(dto.getStartTime(), dto.getEndTime()).getSeconds();
        long minutes = (seconds + 59) / 60; // округление вверх

        if (data.isMonthly()) {
            return calculateMonthly(dto, data, minutes);
        } else {
            return data.getRate().multiply(BigDecimal.valueOf(minutes));
        }
    }

    public BigDecimal calculateMonthly(DataForRatingDTO dto, TariffData data, long totalUsedMinutesThisMonth) {
        BigDecimal result = BigDecimal.ZERO;

        // 1. Проверка: расчёт только если звонок в отчётном (завершённом) месяце
        if (!data.isReportPeriod(dto.getStartTime())) {
            return BigDecimal.ZERO; // текущий месяц — не списываем
        }

        // 2. Всегда списываем абонентскую плату
        result = result.add(data.getBaseValue());

        // 3. Считаем длительность звонка
        long durationInSeconds = Duration.between(dto.getStartTime(), dto.getEndTime()).getSeconds();
        long durationInMinutes = (durationInSeconds + 59) / 60;

        long newTotal = totalUsedMinutesThisMonth + durationInMinutes;

        // 4. Вычисляем сверхлимитные минуты
        long overage = Math.max(0, newTotal - data.getIncludedMinutes());

        if (overage > 0) {
            BigDecimal overageCost = data.getRate().multiply(BigDecimal.valueOf(overage));
            result = result.add(overageCost);
        }

        // 5. Округляем до 0.1 у.е.
        return result.divide(new BigDecimal("0.1"), 0, RoundingMode.UP)
                .multiply(new BigDecimal("0.1"));
    }

}

