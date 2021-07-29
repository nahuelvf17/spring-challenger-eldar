package com.novando.springchallengereldar.payment;

public class AmexPaymentCard extends PaymentTransaction implements IPaymentCard {
    public AmexPaymentCard(int expirationMonth, int expirationYear) {
        super(expirationMonth, expirationYear, coefficient);
    }

    @Override
    public double calculateServiceFee() {
        return roundValue((getExpirationMonth())*getCoefficient());
    }

    final static double coefficient =  0.3;
}
