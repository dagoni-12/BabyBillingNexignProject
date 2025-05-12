package ru.anger.HRS.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.anger.HRS.DTO.ChargeResultDTO;
import ru.anger.HRS.DTO.DataForRatingDTO;
import ru.anger.HRS.calculation.CalculationEngine;
import ru.anger.HRS.input.InputValidator;
import ru.anger.HRS.tariff.TariffData;
import ru.anger.HRS.tariff.TariffManager;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/hrs")
public class RatingController {

    private final InputValidator inputValidator;
    private final TariffManager tariffManager;
    private final CalculationEngine calculationEngine;

    public RatingController(InputValidator inputValidator,
                            TariffManager tariffManager,
                            CalculationEngine calculationEngine) {
        this.inputValidator = inputValidator;
        this.tariffManager = tariffManager;
        this.calculationEngine = calculationEngine;
    }

    @PostMapping("/calculate")
    public ResponseEntity<ChargeResultDTO> calculate(@RequestBody DataForRatingDTO dto) {
        TariffData tariffData = tariffManager.loadTariffData(dto);
        ChargeResultDTO result = calculationEngine.calculate(dto, tariffData);
        return ResponseEntity.ok(result);
    }
}
