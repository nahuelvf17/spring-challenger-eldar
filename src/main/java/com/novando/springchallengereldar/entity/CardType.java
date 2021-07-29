package com.novando.springchallengereldar.entity;

public enum CardType {
    VISA("Visa"),
    AMEX("American Express"),
    NARA("Tarjeta Naranja");

    private final String name;

    CardType(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
