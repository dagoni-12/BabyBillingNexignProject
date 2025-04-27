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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "romashka_subscribers")
@Getter
@Setter
@NoArgsConstructor
public class Subscriber {

    @Id
    @Column(name = "romashka_subscriber_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String msisdn;

    private String fullName;

    @Column(name = "balance", nullable = false, precision = 38, scale = 2)
    private BigDecimal balance;

    @Column(name = "tariff_id", nullable = false)
    private int tariff_id;

    private LocalDateTime last_sync;

    private LocalDateTime registration_date;

    @Column(name = "included_minutes")
    private Integer includedMinutes;

    @Column(name = "last_payment_date")
    private LocalDate lastPaymentDate;

    public Subscriber(String fullName, String msisdn, BigDecimal balance, int tariff_id,
                      LocalDateTime last_sync, LocalDateTime registration_date, int includedMinutes, LocalDate lastPaymentDate) {
        this.fullName = fullName;
        this.msisdn = msisdn;
        this.balance = balance;
        this.tariff_id = tariff_id;
        this.last_sync = last_sync;
        this.registration_date = registration_date;
        this.includedMinutes = includedMinutes;
        this.lastPaymentDate = lastPaymentDate;
    }
}
