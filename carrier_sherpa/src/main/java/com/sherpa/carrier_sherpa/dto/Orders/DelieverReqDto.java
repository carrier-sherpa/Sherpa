package com.sherpa.carrier_sherpa.dto.Orders;

import com.sherpa.carrier_sherpa.dto.type.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DelieverReqDto {

    private Address start;
    private Address end;
}
