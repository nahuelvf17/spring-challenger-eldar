package com.novando.springchallengereldar.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface IPaymentCard {
    public double calculateServiceFee();
    public default double calculateTotalAmount(double amount) {
        double result = amount + ((amount*calculateServiceFee())/100);
        return roundValue(result);
    }
    public default double roundValue(double value){
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
