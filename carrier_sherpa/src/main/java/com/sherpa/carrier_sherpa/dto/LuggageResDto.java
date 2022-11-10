package com.sherpa.carrier_sherpa.dto;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.enums.LuggageType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class LuggageResDto {

    private String luggageId;

    private String memberId;
    private LuggageType size;
    private int number;

    public LuggageResDto(Luggage luggage){
        this.luggageId = luggage.getId();
        this.memberId = luggage.getMember().getId();
        this.size = luggage.getSize();
        this.number = luggage.getNum();
    }

}
