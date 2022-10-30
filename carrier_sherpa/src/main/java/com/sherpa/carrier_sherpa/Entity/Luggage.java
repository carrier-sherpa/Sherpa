package com.sherpa.carrier_sherpa.Entity;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

enum LuggageType { SMALL, MEDIUM, BIG }

@Entity
public class Luggage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String startAddress;
    @NotNull
    private String goalAddress;

    private int type;

}
