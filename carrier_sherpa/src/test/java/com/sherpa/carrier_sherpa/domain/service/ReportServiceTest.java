package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.entity.Report;
import com.sherpa.carrier_sherpa.domain.enums.ReportType;
import com.sherpa.carrier_sherpa.domain.repository.ReportRepository;
import com.sherpa.carrier_sherpa.dto.OrderFormDto;
import com.sherpa.carrier_sherpa.dto.ReportFormDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReportServiceTest {

    @Autowired
    ReportService reportService;
    @Autowired
    OrderService orderService;
    @Autowired
    MemberService memberService;
    @Autowired
    ReportRepository reportRepository;

    MemberServiceTest memberServiceTest = new MemberServiceTest();

//    @BeforeEach
//    public void setupReportTest() {
//
//    }

    public Report createReport(OrderFormDto orderFormDto) {
        ReportFormDto reportFormDto = ReportFormDto.builder()
                .orderFormDto(orderFormDto)
                .senderFormDto(null)
                .courierFormDto(null)
                .reportType(ReportType.LOST)
                .build();

        return reportService.createReport(reportFormDto, orderFormDto);
    }

    public Order createOrder(OrderFormDto orderFormDto) {
        Order order = orderService.createOrder(orderFormDto);

        return order;
    }

    @Test
    void saveReportTest() {
        //given
        OrderFormDto tempOrderDto = OrderFormDto.builder().build();
        Order order = orderService.createOrder(tempOrderDto);
        orderService.saveOrder(order);
        OrderFormDto orderFormDto = orderService.createOrderFormDto(order);
//        orderService.

        Report report = createReport(orderFormDto);

        //when
        Report savedReport = reportService.saveReport(report, order);

        //then
        assertThat(savedReport).isEqualTo(reportRepository.findByOrderId(order.getId()));
    }

    @Test
    void findReportTest() {
        //given
        OrderFormDto tempOrderDto = OrderFormDto.builder().build();
        Order order = orderService.createOrder(tempOrderDto);
        orderService.saveOrder(order);
        OrderFormDto orderFormDto = orderService.createOrderFormDto(order);
//        orderService.

        Report report = createReport(orderFormDto);

        //when
        Report savedReport = reportService.saveReport(report, order);

        //then
        assertThat(savedReport).isEqualTo(reportService.findReport(report.getId()));
    }

    @Test
    void deleteReportTest() {
        //given
        OrderFormDto tempOrderDto = OrderFormDto.builder().build();
        Order order = orderService.createOrder(tempOrderDto);
        orderService.saveOrder(order);
        OrderFormDto orderFormDto = orderService.createOrderFormDto(order);

        Report report = createReport(orderFormDto);

        //when
        Report savedReport = reportService.saveReport(report, order);
        reportService.deleteReport(savedReport.getId());

        //then
        Report findReport = reportService.findReport(savedReport.getId());

        assertThat(findReport).isNull();
//        assertThrows(IllegalStateException.class, () -> {
//                reportService.findReport(orderFormDto);
//        });
    }
}