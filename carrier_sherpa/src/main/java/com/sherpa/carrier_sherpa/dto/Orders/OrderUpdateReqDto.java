package com.sherpa.carrier_sherpa.dto.Orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@Getter
public class OrderUpdateReqDto {
    private String start;
    private String destination;
    private String start_time;
    private String end_time;
    private String luggage_image_url;
}
