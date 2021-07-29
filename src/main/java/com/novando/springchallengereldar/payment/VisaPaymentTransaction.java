package com.novando.springchallengereldar.payment;

public class VisaPaymentTransaction extends PaymentTransaction implements IPaymentTransaction {
    public VisaPaymentTransaction(int expirationMonth, int expirationYear) {
        super(expirationMonth, expirationYear, coefficient);
    }

    @Override
    public double calculateServiceFee() {
        return roundValue((getExpirationMonth()+getExpirationYear())*getCoefficient());
    }

    final static double coefficient =  0.8;

}
