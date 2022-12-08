package com.sherpa.carrier_sherpa.domain.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_USER(4000),
    NOT_LUGGAGE(4001),
    NOT_AUTHORIZATION(4002),
    NOT_ORDER(4003),
    NOT_REPORT(4004),
    INVALID_PASSWORD_FORMAT(4005),
    NULL_VALUE(4006),
    INVALID_COMMAND(4007)
    ;

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
