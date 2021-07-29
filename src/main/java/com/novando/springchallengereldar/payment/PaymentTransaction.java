package com.novando.springchallengereldar.payment;

public abstract class PaymentTransaction {
    private final int expirationMonth;
    private final int expirationYear;
    private final double coefficient;

    public PaymentTransaction(int expirationMonth, int expirationYear, double coefficient) {
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.coefficient = coefficient;
    }

    protected int getExpirationMonth() {
        return this.expirationMonth;
    }

    protected int getExpirationYear(){
        return this.expirationYear;
    }

    protected double getCoefficient(){
        return this.coefficient;
    }
}
