package ru.anger.HRS.tariff;

import lombok.Getter;
import lombok.Setter;
import ru.anger.HRS.model.Tariff;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Getter
@Setter
public class TariffData {

    private String tariffTypeName;
    private BigDecimal rate;
    private int includedMinutes;
    private BigDecimal baseValue;

    public TariffData(Tariff tariff) {
        this.tariffTypeName = tariff.getTariffType().getName();
        this.rate = BigDecimal.valueOf(tariff.getCallType().getCostPerMinute());
        this.includedMinutes = tariff.getIncludedMinutes();
        this.baseValue = BigDecimal.valueOf(tariff.getBaseValue());
    }

    public boolean isMonthly() {
        return "Помесячный".equalsIgnoreCase(tariffTypeName);
    }

    public boolean isReportPeriod(LocalDateTime callTime) {
        YearMonth current = YearMonth.now();
        YearMonth call = YearMonth.from(callTime);
        return call.isBefore(current); // true, если звонок в предыдущем месяце
    }
}
