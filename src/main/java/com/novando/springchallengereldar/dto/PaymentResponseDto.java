package com.novando.springchallengereldar.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaymentResponseDto implements Serializable {
    private Long id;
    private String CardType;
    private double amount;
    private double totalAmount;
}
