package com.novando.springchallengereldar.payment;

import com.novando.springchallengereldar.entity.CardType;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {
    public static IPaymentTransaction createPayment(CardType type, int expirationMonth, int expirationYear){
        switch (type){
            case AMEX:
                return new AmexPaymentTransaction(expirationMonth, expirationYear);
            case VISA:
                return new VisaPaymentTransaction(expirationMonth, expirationYear);
            case NARA:
                return new NaraPaymentTransaction(expirationMonth, expirationYear);
            default:
                return null;
        }
    }
}
