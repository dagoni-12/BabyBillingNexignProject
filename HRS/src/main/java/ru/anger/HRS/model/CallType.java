package ru.anger.HRS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "call_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer callTypeId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer costPerMinute;
}
