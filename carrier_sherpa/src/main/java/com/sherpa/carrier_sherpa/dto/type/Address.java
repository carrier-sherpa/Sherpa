package com.sherpa.carrier_sherpa.dto.type;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Address {
    private Double lat;
    private Double lng;

    public Address(
            Double lat,
            Double lng
    ){
        this.lat = lat;
        this.lng = lng;
    }
}
