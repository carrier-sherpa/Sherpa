package com.sherpa.carrier_sherpa.dto.Orders;

import com.sherpa.carrier_sherpa.dto.Luggage.LuggageReqDto;
import com.sherpa.carrier_sherpa.dto.type.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderReqDto {
    private Address start;
    private Address end;
    private String startTime;
    private String endTime;
    private String luggageImgUrl;
    private List<LuggageReqDto> luggages;
}
