package com.sherpa.carrier_sherpa.dto.Member;

import com.sherpa.carrier_sherpa.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberResDto {

    private String id;
    private String email;

    public MemberResDto(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public MemberResDto(Member member) {
        this.id = member.getId();
        this.id = member.getEmail();
    }
}
