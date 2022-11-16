package com.sherpa.carrier_sherpa.domain.enums;

public enum ReportType {

    BROKEN("BROKEN"),
    LOST("LOST"),
    ETC("ETC");
    private String ReportType;

    ReportType(String type){
        this.ReportType = type;
    }

}
