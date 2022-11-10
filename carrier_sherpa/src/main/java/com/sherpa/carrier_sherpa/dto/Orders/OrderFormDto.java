package com.sherpa.carrier_sherpa.dto.Orders;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderFormDto {
    private Long orderId;

    @Builder
    public OrderFormDto(Long orderId) {
        this.orderId = orderId;
    }
}
