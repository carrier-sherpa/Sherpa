package com.sherpa.carrier_sherpa.dto;

import com.sherpa.carrier_sherpa.domain.enums.LuggageType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderReqDto {
    private String start;
    private String destination;
    private String startTime;
    private String endTime;
    private String luggageImgUrl;
    private List<LuggageReqDto> luggages;
}
