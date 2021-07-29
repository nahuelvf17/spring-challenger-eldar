package com.novando.springchallengereldar.payment;

public class AmexPaymentTransaction extends PaymentTransaction implements IPaymentTransaction {
    public AmexPaymentTransaction(int expirationMonth, int expirationYear) {
        super(expirationMonth, expirationYear, coefficient);
    }

    @Override
    public double calculateServiceFee() {
        return roundValue((getExpirationMonth()+getExpirationYear())*getCoefficient());
    }

    final static double coefficient =  0.3;
}
