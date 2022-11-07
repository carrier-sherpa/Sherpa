package com.sherpa.carrier_sherpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class MemberCreateReqDto {

    @Email
    @NotBlank(message = "이메일은 필수 입력 값입니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다")
    @Length(min = 6, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요")
    private String password;

    @Builder
    public MemberCreateReqDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
