package com.sherpa.carrier_sherpa.dto;

import com.sherpa.carrier_sherpa.domain.enums.ReportType;
import com.sherpa.carrier_sherpa.dto.Member.MemberFormDto;
import com.sherpa.carrier_sherpa.dto.Orders.OrderFormDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class ReportFormDto {

    //TODO: dto 상태로 넣어야 할듯?
    @NotBlank(message = "주문은 필수 입력 값입니다.")
    private OrderFormDto orderFormDto;

    private MemberFormDto senderFormDto;

    private MemberFormDto courierFormDto;

    private String details;

    private ReportType reportType;

    @Builder
    public ReportFormDto(OrderFormDto orderFormDto, MemberFormDto senderFormDto, MemberFormDto courierFormDto, String details, ReportType reportType) {
        this.orderFormDto = orderFormDto;
        this.senderFormDto = senderFormDto;
        this.courierFormDto = courierFormDto;
        this.details = details;
        this.reportType = reportType;
    }
}
