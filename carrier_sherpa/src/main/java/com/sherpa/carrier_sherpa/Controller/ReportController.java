package com.sherpa.carrier_sherpa.Controller;

import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.entity.Report;
import com.sherpa.carrier_sherpa.domain.service.ReportService;
import com.sherpa.carrier_sherpa.dto.Member.MemberFormDto;
import com.sherpa.carrier_sherpa.dto.Orders.OrderFormDto;
import com.sherpa.carrier_sherpa.dto.ReportFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/reports")
@Controller
public class ReportController {

    private final ReportService reportService;

    @ResponseBody
    @GetMapping(value = "/new")
    public String reportForm(Model model) {
        model.addAttribute("reportFormDto", new MemberFormDto());
        return "report/reportForm";
    }

    @ResponseBody
    @PostMapping(value = "/new")
    public String reportForm(@Valid ReportFormDto reportFormDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/";
        }

        try {
            //TODO: OrderFormDto 받는 법을 다른 방식으로 해야댈듯
            Report report = reportService.createReport(reportFormDto, new OrderFormDto());
            reportService.saveReport(report, new Order());
        } catch (IllegalStateException e) {
            //TODO: 에러 알려주는 url로 가야함
            return "";
        }
        return "/";
    }


}
