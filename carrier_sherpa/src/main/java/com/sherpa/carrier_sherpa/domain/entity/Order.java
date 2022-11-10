package com.sherpa.carrier_sherpa.domain.entity;

import com.sherpa.carrier_sherpa.domain.enums.LuggageStatus;
import com.sherpa.carrier_sherpa.domain.service.LuggageService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table(name = "orders")
@Entity
public class Order  extends BaseEntity{

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "traveler_id")
    private Member traveler;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "deliever_id")
    private Member deliever;

    @Column(nullable = false)
    private String start;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String start_time;

    @Column(nullable = false)
    private String end_time;


    private String luggage_image_url;

    @Enumerated(EnumType.STRING)
    private LuggageStatus status;


    @Builder
    public Order(
            Member traveler,
            Member deliever,
            String start,
            String destination,
            String start_time,
            String end_time,
            String luggage_image_url,
            LuggageStatus status) {
        this.traveler = traveler;
        this.deliever = deliever;
        this.start = start;
        this.destination = destination;
        this.start_time = start_time;
        this.end_time = end_time;
        this.luggage_image_url = luggage_image_url;
        this.status = status;
    }

    public Order(
            String id,
            Member traveler,
            Member deliever,
            String start,
            String destination,
            String start_time,
            String end_time,
            String luggage_image_url,
            LuggageStatus status) {
        super(id);
        this.traveler = traveler;
        this.deliever = deliever;
        this.start = start;
        this.destination = destination;
        this.start_time = start_time;
        this.end_time = end_time;
        this.luggage_image_url = luggage_image_url;
        this.status = status;
    }

    public void update(
            String start,
            String destination,
            String start_time,
            String end_time,
            String luggage_image_url) {
        this.start = start;
        this.destination = destination;
        this.start_time = start_time;
        this.end_time = end_time;
        this.luggage_image_url = luggage_image_url;
    }

    public void accept(
            Member deliever
    ){
        this.deliever = deliever;
        this.status = LuggageStatus.ACCEPT;
    }
}