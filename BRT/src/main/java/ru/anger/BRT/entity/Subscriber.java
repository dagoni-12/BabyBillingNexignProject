package ru.anger.BRT.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "romashka_subscribers")
@Getter
@Setter
@NoArgsConstructor
public class Subscriber {

    @Id
    @Column(name = "romashka_subscriber_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String msisdn;

    private String fullName;

    @Column(name = "balance", nullable = false, precision = 38, scale = 2)
    private BigDecimal balance;

    @Column(name = "tariff_id", nullable = false)
    private int tariff_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp last_sync;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp registration_date;

    public Subscriber(String fullName, String msisdn, BigDecimal balance, int tariff_id,
                      Timestamp last_sync, Timestamp registration_date) {
        this.fullName = fullName;
        this.msisdn = msisdn;
        this.balance = balance;
        this.tariff_id = tariff_id;
        this.last_sync = last_sync;
        this.registration_date = registration_date;
    }
}
