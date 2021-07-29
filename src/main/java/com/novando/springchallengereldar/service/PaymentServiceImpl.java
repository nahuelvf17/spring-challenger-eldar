package com.novando.springchallengereldar.service;

import com.novando.springchallengereldar.entity.CardType;
import com.novando.springchallengereldar.entity.Payment;
import com.novando.springchallengereldar.payment.IPaymentTransaction;
import com.novando.springchallengereldar.payment.PaymentFactory;
import com.novando.springchallengereldar.repository.PaymentRepository;
import com.novando.springchallengereldar.utils.CommonUtil;
import com.novando.springchallengereldar.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements IPaymentService{
    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Iterable<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment save(Payment payment) throws Exception {
        if(!commonUtil.validateExpirationDate(payment.getExpirationDate()))
            throw new Exception(Message.SERVICE_EXPIRATION_DATE_ERROR.getLabel());

        IPaymentTransaction paymentTransaction = PaymentFactory.createPayment(CardType.valueOf(payment.getCardType()), commonUtil.getExpirationMonth(payment.getExpirationDate()), commonUtil.getExpirationYear(payment.getExpirationDate()));

        payment.setCalculatedServiceFee(paymentTransaction.calculateServiceFee());
        payment.setTotalAmount(paymentTransaction.calculateTotalAmount(payment.getAmount()));

        return paymentRepository.save(payment);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        paymentRepository.deleteById(id);
    }
}
