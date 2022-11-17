package com.sherpa.carrier_sherpa.Controller;

import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.entity.Report;
import com.sherpa.carrier_sherpa.domain.service.ReportService;
import com.sherpa.carrier_sherpa.dto.Luggage.LuggageResDto;
import com.sherpa.carrier_sherpa.dto.Member.MemberFormDto;
import com.sherpa.carrier_sherpa.dto.Member.MemberResDto;
import com.sherpa.carrier_sherpa.dto.Orders.OrderFormDto;
import com.sherpa.carrier_sherpa.dto.Orders.OrderReqDto;
import com.sherpa.carrier_sherpa.dto.Orders.OrderResDto;
import com.sherpa.carrier_sherpa.dto.Report.ReportReqDto;
import com.sherpa.carrier_sherpa.dto.Report.ReportResDto;
import com.sherpa.carrier_sherpa.dto.ReportFormDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/reports")
@RestController
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/reportId/{Id}")
    public ReportResDto findByReportId(
            HttpServletRequest httpServletRequest,
            @RequestParam ("id") String reportId
    ){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return reportService.findByReportId(memberResDto.getId(),reportId);
    }

    @GetMapping("/reportedId/{id}")
    public List<ReportResDto> findByReportedId(
            HttpServletRequest httpServletRequest,
            @PathVariable ("id") String reportedId
    ){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return reportService.findByReportedId(memberResDto.getId(),reportedId);
    }

    @GetMapping("/reporterId/{id}")
    public List<ReportResDto> findByReporterId(
            HttpServletRequest httpServletRequest,
            @PathVariable ("id") String reporterId
    ){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return reportService.findByReporterId(memberResDto.getId(),reporterId);
    }

    @PostMapping("/{orderId}")
    public ReportResDto create(
            HttpServletRequest httpServletRequest,
            @RequestBody ReportReqDto reportReqDto,
            @PathVariable("orderId") String orderId,
            @RequestParam("report") String reported
    ){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return reportService.create(memberResDto.getId(),reported,orderId,reportReqDto);
    }

}
