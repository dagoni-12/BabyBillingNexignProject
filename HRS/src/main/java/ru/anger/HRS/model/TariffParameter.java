package ru.anger.HRS.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tariff_parameters")
@Getter
@Setter
@NoArgsConstructor
public class TariffParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tariffParameterId;

    @ManyToOne
    @JoinColumn(name = "parameter_id", nullable = false)
    private Parameter parameter;

    @Column(nullable = false, length = 50)
    private String value;

    public TariffParameter(Parameter parameter, String value) {
        this.parameter = parameter;
        this.value = value;
    }
}
