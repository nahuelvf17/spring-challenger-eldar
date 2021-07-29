package com.novando.springchallengereldar.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"}, allowSetters=true)
    private User user;

    private String CardType;

    @NotEmpty
    private String cardNumber;

    @NotEmpty
    private String expirationDate;

    @NotNull
    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private double calculatedServiceFee;

    @NotNull
    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private double totalAmount;

    @NotNull
    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private double amount;
}