package com.sherpa.carrier_sherpa.dto.Orders;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.enums.LuggageStatus;
import com.sherpa.carrier_sherpa.dto.Luggage.LuggageResDto;
import com.sherpa.carrier_sherpa.dto.type.Address;
import com.sherpa.carrier_sherpa.dto.type.Time;
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

    private Address start;

    private Address end;

    private String startTime;
    private String endTime;

    private List<LuggageResDto> luggages;

    public OrderResDto(
            String orderId,
            String travelerId,
            String delieverId,
            Address start,
            Address end,
            String startTime,
            String endTime,
            LuggageStatus status,
            List<LuggageResDto> luggages) {
        this.orderId = orderId;
        this.travelerId = travelerId;
        this.delieverId = delieverId;
        this.status = status;
        this.start = start;
        this.end = end;
        this.startTime = startTime;
        this.endTime = endTime;
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
                    new Address(order.getStart_lat(), order.getStart_lng()),
                    new Address(order.getEnd_lat(),order.getEnd_lng()),
                    order.getStart_time(),
                    order.getEnd_time(),
                    order.getStatus(),
                    luggages);
        }
        return new OrderResDto(
                order.getId(),
                order.getTraveler().getId(),
                String.valueOf(order.getDeliever().getId()),
                new Address(order.getStart_lat(), order.getStart_lng()),
                new Address(order.getEnd_lat(),order.getEnd_lng()),
                order.getStart_time(),
                order.getEnd_time(),
                order.getStatus(),
                luggages);
    }
}