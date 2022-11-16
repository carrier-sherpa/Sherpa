package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Member;
import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.entity.Report;
import com.sherpa.carrier_sherpa.domain.enums.ReportType;
import com.sherpa.carrier_sherpa.domain.exception.BaseException;
import com.sherpa.carrier_sherpa.domain.exception.ErrorCode;
import com.sherpa.carrier_sherpa.domain.repository.MemberRepository;
import com.sherpa.carrier_sherpa.domain.repository.OrderRepository;
import com.sherpa.carrier_sherpa.domain.repository.ReportRepository;
import com.sherpa.carrier_sherpa.dto.Orders.OrderFormDto;
import com.sherpa.carrier_sherpa.dto.Report.ReportReqDto;
import com.sherpa.carrier_sherpa.dto.Report.ReportResDto;
import com.sherpa.carrier_sherpa.dto.ReportFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class ReportService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ReportRepository reportRepository;


    public ReportResDto create(String memberId, String orderId, ReportReqDto reportReqDto) {
        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_USER,
                        "존재하지 않는 유저입니다."
                )
        );
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_ORDER,
                        "존재하지 않는 서비스입니다."
                )
        );

        Report report = new Report(
                order,
                ReportType.valueOf(reportReqDto.getReportType()),
                reportReqDto.getContent()
        );

        String reportId = reportRepository.save(report).getId();

        return new ReportResDto().of(report);
    }

    public ReportResDto findByReportId(
            String memberId,
            String reportId
    ){
        Report report = reportRepository.findById(reportId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_REPORT,
                        "해당하는 신고 내역이 존재하지 않습니다."
                )
        );
        return new ReportResDto().of(report);
    }

    public List<ReportResDto> findByReportedId(
            String memberId,
            String reportedId
    ){
        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_USER,
                        "존재하지 않는 유저입니다."
                )
        );
        Order order = reportRepository.findById(reportedId)
                .orElseThrow(
                        ()->new BaseException(
                                ErrorCode.NOT_REPORT,
                                "해당하는 신고 내역이 존재하지 않습니다."
                        )
                )
                .getOrder();
        return reportRepository.findByReported(reportedId).stream()
                .map(report -> new ReportResDto().of(report))
                .collect(Collectors.toList());
    }
}
