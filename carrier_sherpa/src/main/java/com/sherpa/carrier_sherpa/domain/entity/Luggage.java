package com.sherpa.carrier_sherpa.domain.entity;

import com.sherpa.carrier_sherpa.domain.enums.LuggageStatus;
import com.sherpa.carrier_sherpa.domain.enums.LuggageType;
import com.sherpa.carrier_sherpa.domain.enums.MemberRole;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Table(name = "luggage")
@Entity
public class Luggage  extends BaseEntity{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "luggage_id")
//    private Long id;
    // 지우고 다시해도 AutoIncrement 적용. 다시 초기화 하는 방법은?

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id",name = "member_id")
    private Member member;
//  문맥상 owner라는 value 네이밍이 맞지만 Error:link falilure 발생. member로 바꿈

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id",name = "order_id")
    private Order order;
    @Column(nullable = false)
    private int num;

    @Enumerated(EnumType.STRING)
    private LuggageType size;


    // 왜 DB에는 luggageType이라 하며 오류나고 luggage_type이라 저장해야 되는건지...?

    @Builder
    public Luggage(
            Member member,
            Order order,
            LuggageType size,
            int num
    ) {
        this.order = order;
        this.member = member;
        this.size = size;
        this.num = num;
    }

    public Luggage(
            String id,
            Member member,
            LuggageType size,
            int num
    ) {
        super(id);
        this.member = member;
        this.size = size;
        this.num = num;
    }

    public void update(
            LuggageType size,
            int num
    ){
        this.size = size;
        this.num = num;
    }

}