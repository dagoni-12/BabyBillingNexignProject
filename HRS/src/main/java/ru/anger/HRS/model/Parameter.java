package ru.anger.HRS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parameters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parameterId;

    @Column(nullable = false, length = 50)
    private String name;

    private String unit;

    @Column(nullable = false, length = 20)
    private String dataType;
}