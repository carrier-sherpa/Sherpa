package com.sherpa.carrier_sherpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class LuggageResDto {

    private Long luggageId;

    private String memberId;

//    public static LuggageResDto of(Long luggageId,String memberId){
//        return new LuggageResDto()
//    }
}
