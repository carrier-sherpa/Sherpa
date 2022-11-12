package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.entity.Report;
import com.sherpa.carrier_sherpa.domain.enums.ReportType;
import com.sherpa.carrier_sherpa.domain.repository.ReportRepository;
import com.sherpa.carrier_sherpa.dto.Orders.OrderFormDto;
import com.sherpa.carrier_sherpa.dto.ReportFormDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void saveReportTest() {

    }

    @Test
    void findReportTest() {

    }

    @Test
    void deleteReportTest() {

    }
}