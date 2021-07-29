package com.novando.springchallengereldar.service;

import com.novando.springchallengereldar.entity.Payment;
import java.util.Optional;

public interface IPaymentService {
    public Optional<Payment> findById(Long id);
    public Iterable<Payment> findAll();
    public Payment save(Payment payment) throws Exception;
    public void deleteById(Long id) throws Exception;
}
