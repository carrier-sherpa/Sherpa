package com.sherpa.carrier_sherpa.dto;

import com.sherpa.carrier_sherpa.domain.enums.LuggageType;
import lombok.Getter;

@Getter
public class LuggageListVO {
    private int num;
    private LuggageType size;
}
