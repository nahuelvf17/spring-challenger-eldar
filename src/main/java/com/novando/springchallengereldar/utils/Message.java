package com.novando.springchallengereldar.utils;

public enum Message {
    CONTROLLER_PAYMENT_CARD_TYPE_NOT_FOUND("El tipo de tarjeta pasado como parametro no existe"),
    CONTROLLER_PAYMENT_SAVE_ERROR("Error en la grabacion de la transaccion"),
    CONTROLLER_PAYMENT_SAVE_SUCCESS("Se grabo exitosamente la transaccion"),
    CONTROLLER_REGISTER_SUCCESS("Usuario creado con exito"),
    CONTROLLER_LOGIN_USER_NOT_FOUND("El usuario no existe, debe registrarse"),
    CONTROLLER_LOGIN_SUCCESS("Usuario logeado correctamente"),
    CONTROLLER_LOGIN_PASSWORD_ERROR("La contraseña no coincide, ingrese la contraseña correcta, intente nuevamente"),
    CONTROLLER_PAYMENT_CARD_TYPE_ERROR("El tipo de tarjeta pasado como parametro no existe"),
    SERVICE_EXPIRATION_DATE_ERROR("Error en fecha de expiracion, formato valido MMYY");

    private final String label;

    Message(String label){
        this.label=label;
    }

    public String getLabel(){
        return label;
    }
}
