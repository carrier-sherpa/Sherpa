package com.sherpa.carrier_sherpa.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table(name = "luggage")
@Entity
public class Luggage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "luggage_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member owner;

    private int luggageType;
}
