package com.sherpa.carrier_sherpa.domain.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Table(name = "cafe")
@Entity
public class Cafe extends BaseEntity{

    @Column(nullable = false)
    private String cafeName;

    private String cafe_image_url;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private Double lng;

    @Column(nullable = false)
    private int luggageNum;

    private String content;

    public void entrust(int luggageNum){
        this.luggageNum -= luggageNum;
    }
}