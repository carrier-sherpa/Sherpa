package com.sherpa.carrier_sherpa.dto.Orders;

import com.sherpa.carrier_sherpa.dto.type.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DelieverReqDto {

    private Address start;
    private Address end;

    public DelieverReqDto(
            Double startLat,
            Double startLng,
            Double endLat,
            Double endLng){
        this.start = new Address(startLat, startLng);
        this.end = new Address(endLat, endLng);
    }
}
