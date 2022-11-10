package com.sherpa.carrier_sherpa.domain.enums;

public enum LuggageType {
    SMALL ("SMALL"),
    MEDIUM ("MEDIUM"),
    BIG("BIG");
    private String luggageType;

    LuggageType(String type){
        this.luggageType = type;
    }
}
