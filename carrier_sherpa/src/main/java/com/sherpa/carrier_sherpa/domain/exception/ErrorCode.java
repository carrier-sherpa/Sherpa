package com.sherpa.carrier_sherpa.domain.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_USER(4000),
    NOT_LUGGAGE(4001),
    NOT_AUTHORIZATION(4002),
    NOT_ORDER(4003)
    ;

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
