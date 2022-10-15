package com.sherpa.carrier_sherpa.domain.entity;

import com.sherpa.carrier_sherpa.domain.enums.MemberRole;
import com.sherpa.carrier_sherpa.dto.MemberFormDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder
    public Member(Long id, String email, String password, MemberRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //여기 왜 static이지?
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
//        Member member = new Member();

        return Member.builder()
                .email(memberFormDto.getEmail())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .role(MemberRole.USER)
                .build();
    }
}
