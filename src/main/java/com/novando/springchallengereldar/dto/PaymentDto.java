package com.novando.springchallengereldar.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
public class PaymentDto implements Serializable {
    @NotEmpty
    @Email
    private String user;
    @NotEmpty
    private String password;
    @NotEmpty
    private String CardType;
    @NotEmpty
    @Length(min = 14, max = 19)
    private String cardNumber;
    @NotEmpty
    @Length(min = 4, max = 4, message ="La fecha de vencimiento debe tener 4 caracteres MMYY")
    private String expirationDate;
    @NotNull
    @Positive
    private double amount;
}
