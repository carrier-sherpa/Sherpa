package com.sherpa.carrier_sherpa.domain.enums;

public enum MemberRole {
    USER("USER"),
    ADMIN("ADMIN"),
    REPORTED("REPORTED");
    private String MemberRole;

    MemberRole(String type){
        this.MemberRole = type;
    }

}
