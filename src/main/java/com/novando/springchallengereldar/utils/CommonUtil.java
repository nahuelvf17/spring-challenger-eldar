package com.novando.springchallengereldar.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommonUtil {
    public ResponseEntity<?> validar(BindingResult result){
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err->{
            errores.put(err.getField(), "El campo " + err.getField() +  " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errores);
    }
    public boolean validateExpirationDate(final String expirationDate){
        if(getExpirationMonth(expirationDate)==0 || getExpirationYear(expirationDate)==0){
            return false;
        }
        return true;
    }
    public Integer getExpirationMonth(final String expirationDate){
        return Integer.valueOf(expirationDate.substring(0, 2));
    }

    public Integer getExpirationYear(final String expirationDate){
        return Integer.valueOf(expirationDate.substring(2, 4));
    }
}
