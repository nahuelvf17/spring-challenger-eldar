package com.novando.springchallengereldar.payment;

public class NaraPaymentTransaction extends PaymentTransaction implements IPaymentTransaction {

    public NaraPaymentTransaction(int expirationMonth, int expirationYear) {
        super(expirationMonth, expirationYear, coefficient);
    }

    @Override
    public double calculateServiceFee() {
        return roundValue((getExpirationMonth()+getExpirationYear())*getCoefficient());
    }

    final static double coefficient =  0.5;

}
