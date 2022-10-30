package com.sherpa.carrier_sherpa.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "member_id", name = "sender_id")
    private Member sender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "member_id", name = "courier_id")
    private Member courier;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "luggage_id")
    private Luggage luggage;

    private String start;

    private String goal;

    @Builder
    public Order(Long id, Member sender, Member courier, Luggage luggage, String start, String goal) {
        this.id = id;
        this.sender = sender;
        this.courier = courier;
        this.luggage = luggage;
        this.start = start;
        this.goal = goal;
    }
}
