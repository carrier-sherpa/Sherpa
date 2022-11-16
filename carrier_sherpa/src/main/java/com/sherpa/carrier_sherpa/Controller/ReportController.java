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
@Controller
public class ReportController {

    private final ReportService reportService;

//    @GetMapping("/reporterId/{id}")
//    public List<ReportResDto> findByReporterId(
//            HttpServletRequest httpServletRequest,
//            @RequestParam ("id") String reporterId
//    ){
//        HttpSession httpSession = httpServletRequest.getSession();
//        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
//        return reportService.findByReporterId(memberResDto.getId(),reporterId);
//    }
//
//    @GetMapping("/reportedId/{id}")
//    public List<ReportResDto> findByReportedId(
//            HttpServletRequest httpServletRequest,
//            @RequestParam ("id") String reportedId
//    ){
//        HttpSession httpSession = httpServletRequest.getSession();
//        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
//        return reportService.findByReportedId(memberResDto.getId(),reportedId);
//    }
//
//    @GetMapping("/orderId/{orderId}")
//    public List<ReportResDto> findByOrderId(
//            HttpServletRequest httpServletRequest,
//            @RequestParam("orderId") String orderId
//    ){
//        HttpSession httpSession = httpServletRequest.getSession();
//        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
//        return reportService.findByMemberId(memberResDto.getId(),orderId);
//    }

    @PostMapping("/{orderId}")
    public ReportResDto create(
            HttpServletRequest httpServletRequest,
            @RequestBody ReportReqDto reportReqDto,
            @PathVariable("orderId") String orderId
    ){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return reportService.create(memberResDto.getId(),orderId,reportReqDto);
    }

}
