package com.sherpa.carrier_sherpa.domain.entity;

import com.sherpa.carrier_sherpa.domain.enums.MemberRole;
import com.sherpa.carrier_sherpa.dto.Member.MemberCreateReqDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table(name = "member")
@Entity
public class Member extends BaseEntity{
    @Column(name = "email",unique = true)
    private String email;

    @Column(name ="password")
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private float tripEnergy;

    private int delieverTime;
    @Builder
    public Member(String email, String password, MemberRole role, float tripEnergy, int delieverTime) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.tripEnergy = tripEnergy;
        this.delieverTime = delieverTime;
    }

    public Member(String id,String email, String password, MemberRole role, float tripEnergy, int delieverTime) {
        super(id);
        this.email = email;
        this.password = password;
        this.role = role;
        this.tripEnergy = tripEnergy;
        this.delieverTime = delieverTime;
    }

    public void setTripEnergy(int tripScore){
        int travel_n = 5;
        this.tripEnergy += (tripScore - 3) * Math.log(travel_n);
    }

}
