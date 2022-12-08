package com.sherpa.carrier_sherpa.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Table(name = "terminal")
@Entity
public class Terminal {

    @Id
    private int id;
    private String name;

    private Double lat;

    private Double lng;
}
