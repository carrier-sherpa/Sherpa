package com.sherpa.carrier_sherpa.dto.Orders;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.enums.LuggageStatus;
import com.sherpa.carrier_sherpa.dto.Luggage.LuggageResDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResDto {

    private String orderId;
    private String travelerId;
    private String delieverId;
    private LuggageStatus status;

    private List<LuggageResDto> luggages;

    public OrderResDto(
            String orderId,
            String travelerId,
            String delieverId,
            LuggageStatus status,
            List<LuggageResDto> luggages) {
        this.orderId = orderId;
        this.travelerId = travelerId;
        this.delieverId = delieverId;
        this.status = status;
        this.luggages = luggages;
    }

    public static OrderResDto of(
            Order order,
            List<LuggageResDto> luggages
    ){
        if (order.getDeliever()==null) {
            System.out.println("test");
            return new OrderResDto(
                    order.getId(),
                    order.getTraveler().getId(),
                   "None",
                    LuggageStatus.ACCEPT,
                    luggages);
        }
        return new OrderResDto(
                order.getId(),
                order.getTraveler().getId(),
                String.valueOf(order.getDeliever().getId()),
                LuggageStatus.ACCEPT,
                luggages);
    }
}