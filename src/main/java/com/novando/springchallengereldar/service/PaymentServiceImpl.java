package com.novando.springchallengereldar.service;

import com.novando.springchallengereldar.dto.PaymentResponseDto;
import com.novando.springchallengereldar.entity.CardType;
import com.novando.springchallengereldar.entity.Payment;
import com.novando.springchallengereldar.payment.IPaymentCard;
import com.novando.springchallengereldar.payment.PaymentCardFactory;
import com.novando.springchallengereldar.payment.PaymentTransaction;
import com.novando.springchallengereldar.repository.PaymentRepository;
import com.novando.springchallengereldar.utils.CommonUtil;
import com.novando.springchallengereldar.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements IPaymentService{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserServiceImpl userService;

    @Transactional(readOnly = true)
    @Override
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Transactional
    @Override
    public Payment save(Payment payment) throws Exception {
        if(!commonUtil.validateExpirationDate(payment.getExpirationDate()))
            throw new Exception(Message.SERVICE_EXPIRATION_DATE_ERROR.getLabel());

        IPaymentCard paymentTransaction = PaymentCardFactory.createPayment(CardType.valueOf(payment.getCardType()), commonUtil.getExpirationMonth(payment.getExpirationDate()), commonUtil.getExpirationYear(payment.getExpirationDate()));

        payment.setCalculatedServiceFee(paymentTransaction.calculateServiceFee());
        payment.setTotalAmount(paymentTransaction.calculateTotalAmount(payment.getAmount()));

        return paymentRepository.save(payment);
    }

    @Transactional
    @Override
    public void deleteById(Long id) throws Exception {
        paymentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Payment> findByFilter(String user) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Payment> criteriaQuery = criteriaBuilder.createQuery(Payment.class);
        Root<Payment> paymentRoot = criteriaQuery.from(Payment.class);
        criteriaQuery.select(paymentRoot);

        // ORDER BY
        criteriaQuery.orderBy(criteriaBuilder.desc(paymentRoot.get("totalAmount")));

        TypedQuery<Payment> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Payment> paymentList = typedQuery.getResultList();
        List<Payment> listFilterByUser = new ArrayList<>();

        if(!user.isEmpty()) {

            listFilterByUser= paymentList.stream().filter(payment->payment.getUser().getEmail().compareTo(user)==0).collect(Collectors.toList());

        }

        return listFilterByUser;

    }
}
