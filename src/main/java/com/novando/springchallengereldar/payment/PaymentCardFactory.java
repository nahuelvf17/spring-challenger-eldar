package com.novando.springchallengereldar.payment;

import com.novando.springchallengereldar.entity.CardType;
import org.springframework.stereotype.Component;

@Component
public class PaymentCardFactory {
    public static IPaymentCard createPayment(CardType type, int expirationMonth, int expirationYear){
        switch (type){
            case AMEX:
                return new AmexPaymentCard(expirationMonth, expirationYear);
            case VISA:
                return new VisaPaymentCard(expirationMonth, expirationYear);
            case NARA:
                return new NaraPaymentCard(expirationMonth, expirationYear);
            default:
                return null;
        }
    }
}
