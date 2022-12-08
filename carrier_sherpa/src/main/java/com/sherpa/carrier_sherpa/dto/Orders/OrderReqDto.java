package com.sherpa.carrier_sherpa.dto.Orders;

import com.sherpa.carrier_sherpa.dto.Luggage.LuggageReqDto;
import com.sherpa.carrier_sherpa.dto.type.Address;
import com.sherpa.carrier_sherpa.dto.type.Time;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderReqDto {
    private Address start;
    private Address end;
    private Time startTime;
    private Time endTime;
    private String luggageImgUrl;
    private List<LuggageReqDto> luggages;
    private String cafeId;
}
