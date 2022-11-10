package com.sherpa.carrier_sherpa.domain.entity;

import com.sherpa.carrier_sherpa.domain.enums.MemberRole;
import com.sherpa.carrier_sherpa.dto.MemberCreateReqDto;
import com.sherpa.carrier_sherpa.dto.MemberFormDto;
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

    @Builder
    public Member(String email, String password, MemberRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Member(String id,String email, String password, MemberRole role) {
        super(id);
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Member create(MemberCreateReqDto memberCreateReqDto) {
        return new Member(memberCreateReqDto.getEmail(), memberCreateReqDto.getPassword(), MemberRole.USER);
    }

}
