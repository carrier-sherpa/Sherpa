package com.sherpa.carrier_sherpa.domain.entity;

import com.sherpa.carrier_sherpa.domain.enums.LuggageStatus;
import com.sherpa.carrier_sherpa.domain.service.LuggageService;
import com.sherpa.carrier_sherpa.dto.type.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table(name = "orders")
@Entity
public class Order  extends BaseEntity{

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "traveler_id")
    private Member traveler;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "deliever_id")
    private Member deliever;

    @Column(nullable = false)
    private String start_time;

    @Column(nullable = false)
    private String end_time;

    // 이넘이 위도
    @Column(nullable = false)
    private float start_lat;

    //이넘잉 경도
    @Column(nullable = false)
    private float start_lng;

    @Column(nullable = false)
    private float end_lat;

    @Column(nullable = false)
    private float end_lng;

    private String luggage_image_url;

    @Enumerated(EnumType.STRING)
    private LuggageStatus status;



    @Builder
    public Order(
            Member traveler,
            Member deliever,
            String start_time,
            String end_time,
            Address start,
            Address end,
            String luggage_image_url,
            LuggageStatus status) {
        this.traveler = traveler;
        this.deliever = deliever;
        this.start_time = start_time;
        this.end_time = end_time;
        this.start_lat = start.getLat();
        this.start_lng = start.getLng();
        this.end_lat = end.getLat();
        this.end_lng = end.getLng();
        this.luggage_image_url = luggage_image_url;
        this.status = status;
    }

    public Order(
            String id,
            Member traveler,
            Member deliever,
            Address start,
            Address end,
            String start_time,
            String end_time,
            String luggage_image_url,
            LuggageStatus status) {
        super(id);
        this.traveler = traveler;
        this.deliever = deliever;
        this.start_time = start_time;
        this.end_time = end_time;
        this.start_lat = start.getLat();
        this.start_lng = start.getLng();
        this.end_lat = end.getLat();
        this.end_lng = end.getLng();
        this.luggage_image_url = luggage_image_url;
        this.status = status;
    }

    public void update(
            Address start,
            Address end,
            String start_time,
            String end_time,
            String luggage_image_url) {
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