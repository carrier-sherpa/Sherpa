package com.sherpa.carrier_sherpa.dto.Orders;

import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.enums.LuggageStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderResDto {

    private String orderId;
    private String travelerId;
    private String delieverId;
    private LuggageStatus status;


    public OrderResDto(String orderId, String travelerId, String delieverId, LuggageStatus status) {
        this.orderId = orderId;
        this.travelerId = travelerId;
        this.delieverId = delieverId;
        this.status = status;
    }

    public static OrderResDto of(
            Order order
    ){
        return new OrderResDto(
                order.getId(),
                order.getTraveler().getId(),
                order.getDeliever().getId(),
                LuggageStatus.ACCEPT);
    }
}