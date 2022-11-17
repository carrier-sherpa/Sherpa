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


    public ReportResDto create(String memberId, String reported, String orderId, ReportReqDto reportReqDto) {

        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_USER,
                        "존재하지 않는 유저입니다."
                )
        );

        Member reportedMember = memberRepository.findById(reported).orElseThrow(
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
        // 신고자가 order와 관계 X validation 처리
        if (!loginMember.equals(order.getDeliever()) && !loginMember.equals(order.getTraveler())){
            throw new BaseException(
                    ErrorCode.NOT_AUTHORIZATION,
                    "신고 권한이 없습니다."
            );
        }


        // Order와 전혀 관계없는 member를 신고 가능 validation 처리 필요
        if (!reported.equals(order.getDeliever().getId()) && !reported.equals(order.getTraveler().getId())){
            throw new BaseException(
                    ErrorCode.NOT_AUTHORIZATION,
                    "잘못된 신고 대상입니다."
            );
        }

        Report report = Report.builder()
                .reporter(loginMember)
                .reported(memberRepository.findById(reported).orElseThrow(
                        () -> new BaseException(
                                ErrorCode.NOT_USER,
                                "존재하지 않는 유저입니다."
                        )
                ))
                .order(order)
                .reportType(ReportType.valueOf(reportReqDto.getReportType()))
                .content(reportReqDto.getContent())
                .tripScore(reportReqDto.getTripScore())
                .build();
        reportedMember.setTripEnergy(reportReqDto.getTripScore());
        memberRepository.save(reportedMember);
//        String reportId = reportRepository.save(report).getId();

        return new ReportResDto().of(reportRepository.save(report));
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
        return reportRepository.findByReported(reportedId).stream()
                .map(report -> new ReportResDto().of(report))
                .collect(Collectors.toList());
    }
    public List<ReportResDto> findByReporterId(
            String memberId,
            String reporterId
    ){

        return reportRepository.findByReporter(reporterId).stream()
                .map(report -> new ReportResDto().of(report))
                .collect(Collectors.toList());

    }
}
