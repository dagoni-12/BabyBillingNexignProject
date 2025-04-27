package ru.anger.HRS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tariff_type")
@Getter
@Setter
@NoArgsConstructor
public class TariffType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tariffTypeId;

    @Column(nullable = false, length = 50)
    private String name;

    public TariffType(String name) {
        this.name = name;
    }
}
