package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.entity.Report;
import com.sherpa.carrier_sherpa.domain.repository.MemberRepository;
import com.sherpa.carrier_sherpa.domain.repository.OrderRepository;
import com.sherpa.carrier_sherpa.domain.repository.ReportRepository;
import com.sherpa.carrier_sherpa.dto.OrderFormDto;
import com.sherpa.carrier_sherpa.dto.ReportFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReportService {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ReportRepository reportRepository;
    private final OrderRepository orderRepository;

    public Report saveReport(Report report, Order order) {
        validateDuplicateReport(report, order);
        return reportRepository.save(report);
    }

    private void validateDuplicateReport(Report report, Order order) {
        Report findOrder = reportRepository.findByOrderId(order.getId());
        if (findOrder != null) {
            throw new IllegalStateException("이미 신고가 들어갔습니다.");
        }
    }

    public Report createReport(ReportFormDto reportFormDto, OrderFormDto orderFormDto) {

        return Report.builder()
                .order(orderRepository.findById(orderFormDto.getOrderId())
                        .orElseThrow(() -> new IllegalStateException("해당하는 order가 업습니다")))
//                .sender(memberService.createMember(reportFormDto.getSenderFormDto()))
//                .courier(memberService.createMember(reportFormDto.getCourierFormDto()))
                .reportType(reportFormDto.getReportType())
                .build();
    }

    public Report findReport(Long reportId) {
        return reportRepository.findById(reportId).orElseThrow(() ->
                new IllegalStateException());
    }

    public void deleteReport(Long reportId) {
        reportRepository.deleteById(reportId);
    }

    public ReportFormDto createReportFormDto(Report report) {
        return null;
    }
}
