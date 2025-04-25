package ru.anger.HRS.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tariff")
@Getter
@Setter
@NoArgsConstructor
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tariffId;

    @ManyToOne
    @JoinColumn(name = "tariff_type_id", nullable = false)
    private TariffType tariffType;

    @ManyToOne
    @JoinColumn(name = "call_type_id", nullable = false)
    private CallType callType;

    @ManyToOne
    @JoinColumn(name = "parameter_id", nullable = false)
    private Parameter parameter;

    @Column(nullable = false, length = 50)
    private String tariffName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer includedMinutes;

    @Column(nullable = false)
    private Integer baseValue;

    public Tariff(TariffType tariffType, CallType callType, Parameter parameter, String tariffName, String description, Integer includedMinutes, Integer baseValue) {
        this.tariffType = tariffType;
        this.callType = callType;
        this.parameter = parameter;
        this.tariffName = tariffName;
        this.description = description;
        this.includedMinutes = includedMinutes;
        this.baseValue = baseValue;
    }
}