package com.sherpa.carrier_sherpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class LuggageReqDto {

    @NotBlank(message = "Luggage ID는 필수 입력 값입니다")
    private Long luggageId;

    @NotBlank(message = "member ID는 필수 입력 값입니다")
    private String memberId;

}
