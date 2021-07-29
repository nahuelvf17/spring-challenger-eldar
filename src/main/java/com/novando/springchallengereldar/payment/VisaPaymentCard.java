package com.novando.springchallengereldar.payment;

public class VisaPaymentCard extends PaymentTransaction implements IPaymentCard {
    public VisaPaymentCard(int expirationMonth, int expirationYear) {
        super(expirationMonth, expirationYear, coefficient);
    }

    @Override
    public double calculateServiceFee() {
        return roundValue((getExpirationMonth()+getExpirationYear())*getCoefficient());
    }

    final static double coefficient =  0.8;

}
