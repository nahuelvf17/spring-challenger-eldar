package com.novando.springchallengereldar.controller;

import com.novando.springchallengereldar.config.BcryptGenerator;
import com.novando.springchallengereldar.dto.PaymentDto;
import com.novando.springchallengereldar.entity.CardType;
import com.novando.springchallengereldar.entity.Payment;
import com.novando.springchallengereldar.entity.User;
import com.novando.springchallengereldar.service.PaymentServiceImpl;
import com.novando.springchallengereldar.service.UserServiceImpl;
import com.novando.springchallengereldar.utils.CommonUtil;
import com.novando.springchallengereldar.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PaymentController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    BcryptGenerator bcryptGenerator;

    @Autowired
    private CommonUtil commonUtil;

    @GetMapping("/payment")
    public ResponseEntity<?> paymentTransaction() {
        return ResponseEntity.ok().body(paymentService.findAll());
    }
    @PostMapping("/payment")
    public ResponseEntity<?> paymentTransaction(@Valid @RequestBody PaymentDto paymentDto, BindingResult result) {

        if (result.hasErrors()) {
            return commonUtil.validar(result);
        }

        final Optional<User> userDb = userService.findUserByEmail(paymentDto.getUser());

        if (!userDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.CONTROLLER_LOGIN_USER_NOT_FOUND.getLabel());
        }

        if (!bcryptGenerator.passwordDecoder(paymentDto.getPassword(),userDb.get().getPassword()))
            return ResponseEntity.badRequest().body(Message.CONTROLLER_LOGIN_PASSWORD_ERROR.getLabel());

        CardType cardType;
        try {
            cardType = CardType.valueOf(paymentDto.getCardType());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.CONTROLLER_PAYMENT_CARD_TYPE_NOT_FOUND.getLabel());
        }

        Payment payment = new Payment();
        payment.setUser(userDb.get());
        payment.setCardNumber(paymentDto.getCardNumber());
        payment.setExpirationDate(paymentDto.getExpirationDate());
        payment.setCardType(cardType.name());
        payment.setAmount(paymentDto.getAmount());

        try {
            paymentService.save(payment);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(Message.CONTROLLER_PAYMENT_SAVE_SUCCESS.getLabel());
    }
}
