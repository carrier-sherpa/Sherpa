package com.sherpa.carrier_sherpa.Controller;

import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.service.OrderService;
import com.sherpa.carrier_sherpa.dto.Luggage.LuggageResDto;
import com.sherpa.carrier_sherpa.dto.Member.MemberResDto;
import com.sherpa.carrier_sherpa.dto.Orders.DelieverReqDto;
import com.sherpa.carrier_sherpa.dto.Orders.OrderReqDto;
import com.sherpa.carrier_sherpa.dto.Orders.OrderResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orderId/{order}")
    public OrderResDto findById(
            HttpServletRequest httpServletRequest,
            @PathVariable("order") String orderId
    ){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return orderService.findById(memberResDto.getId(),orderId);
    }

    @GetMapping("/memberId")
    public List<OrderResDto> findByMemberId(
            HttpServletRequest httpServletRequest
    ){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return orderService.findByMemberId(memberResDto.getId());
    }

    @GetMapping("/distance")
    public List<OrderResDto> findByDistance(
            HttpServletRequest httpServletRequest,
            @RequestParam("startLat") Double startLat,
            @RequestParam("startLng") Double startLng,
            @RequestParam("endLat") Double endLat,
            @RequestParam("endLng") Double endLng
    ) {
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return orderService.findByDistance(
                memberResDto.getId(),
                new DelieverReqDto(startLat, startLng, endLat, endLng)
        );
     }
    @PostMapping("")
    public OrderResDto create(
            HttpServletRequest httpServletRequest,
            @RequestBody OrderReqDto orderReqDto
    ){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return orderService.create(memberResDto.getId(),orderReqDto);
    }

    @PostMapping("/{orderid}")
    public OrderResDto accept(
            HttpServletRequest httpServletRequest,
            @PathVariable("orderid") String orderId
    ){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return orderService.accept(memberResDto.getId(),orderId);
    }
    @PatchMapping("/{id}")
    public OrderResDto update(
            HttpServletRequest httpServletRequest,
            @RequestBody OrderReqDto orderReqDto,
            @PathVariable("id") String orderId
    ){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return orderService.update(memberResDto.getId(),orderId, orderReqDto);
    }

    @DeleteMapping("/{orderId}")
    public OrderResDto delete(
            HttpServletRequest httpServletRequestl,
            @PathVariable("orderId") String orderId
    ){
        HttpSession httpSession = httpServletRequestl.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return orderService.delete(memberResDto.getId(),orderId);
    }
}