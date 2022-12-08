package com.sherpa.carrier_sherpa.Controller;

import com.sherpa.carrier_sherpa.domain.service.CafeService;
import com.sherpa.carrier_sherpa.dto.Cafe.CafeReqDto;
import com.sherpa.carrier_sherpa.dto.Cafe.CafeResDto;
import com.sherpa.carrier_sherpa.dto.Member.MemberResDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController()
@RequestMapping("/cafe")
public class CafeController {

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping("")
    public List<CafeResDto> findByDistance(
            HttpServletRequest httpServletRequest,
            @RequestParam("lat") Double lat,
            @RequestParam("lng") Double lng
            ) {
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return cafeService.findByDistance(memberResDto.getId(), lat, lng);
    }

    @GetMapping("/cafeId")
    public CafeResDto findById(
            HttpServletRequest httpServletRequest,
            @RequestParam("cafeId") String cafeId
    ){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return cafeService.findById(memberResDto.getId(), cafeId);
    }

//    @PostMapping()
//    public CafeResDto entrust(
//            HttpServletRequest httpServletRequest,
//            @RequestParam("cafeId") String cafeId
//    ){
//        HttpSession httpSession = httpServletRequest.getSession();
//        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
//        return cafeService.entrust(memberResDto.getId(),cafeId);
//    }
}