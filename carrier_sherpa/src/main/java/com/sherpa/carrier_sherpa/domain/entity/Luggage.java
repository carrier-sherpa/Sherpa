package com.sherpa.carrier_sherpa.domain.entity;

import com.sherpa.carrier_sherpa.domain.enums.LuggageType;
import com.sherpa.carrier_sherpa.domain.enums.MemberRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table(name = "luggage")
@Entity
public class Luggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "luggage_id")
    private Long id;
    // 지우고 다시해도 AutoIncrement 적용. 다시 초기화 하는 방법은?

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;
//  문맥상 owner라는 value 네이밍이 맞지만 Error:link falilure 발생. member로 바꿈

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
    private LuggageType size;

    // 왜 DB에는 luggageType이라 하며 오류나고 luggage_type이라 저장해야 되는건지...?

    public Luggage(
            Long id,
            Member member,
            String start,
            String destination,
            String start_time,
            String end_time,
            String luggage_image_url,
            LuggageType size
    ){
        this.id = id;
        this.member = member;
        this.start = start;
        this.destination = destination;
        this.start_time = start_time;
        this.end_time = end_time;
        this.luggage_image_url = luggage_image_url;
        this.size = size;
    }
    public void update(
            Member member,
            String start,
            String destination,
            String start_time,
            String end_time,
            String luggage_image_url,
            LuggageType size
    ){
        this.member = member;
        this.start = start;
        this.destination = destination;
        this.start_time = start_time;
        this.end_time = end_time;
        this.luggage_image_url = luggage_image_url;
        this.size = size;
    }

}