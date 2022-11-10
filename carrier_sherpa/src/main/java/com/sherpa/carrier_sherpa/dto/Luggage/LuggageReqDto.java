package com.sherpa.carrier_sherpa.dto.Luggage;

import com.sherpa.carrier_sherpa.domain.enums.LuggageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class LuggageReqDto {
    private int num;

    private String size;
}
