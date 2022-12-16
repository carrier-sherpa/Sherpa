package com.sherpa.carrier_sherpa.Controller;


import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.entity.Member;
import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.service.LuggageService;
import com.sherpa.carrier_sherpa.domain.service.MemberService;
import com.sherpa.carrier_sherpa.dto.Member.MemberCreateReqDto;
import com.sherpa.carrier_sherpa.dto.Member.MemberFormDto;
import com.sherpa.carrier_sherpa.dto.Member.MemberResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final LuggageService luggageService;

    @ResponseBody
    @PostMapping("/signup")
    public MemberResDto signUp(
            HttpServletRequest httpServletRequest,
            @RequestBody MemberCreateReqDto memberCreateReqDto
    ){
        return memberService.signUp(memberCreateReqDto);
    }

    @ResponseBody
    @PostMapping("/signin")
    public MemberResDto signIn(
            HttpServletRequest httpServletRequest,
            @RequestBody MemberFormDto memberFormDto
    ){
        HttpSession session = httpServletRequest.getSession();
        MemberResDto loginMember = memberService.signIn(memberFormDto);
        session.setAttribute("loginMember",memberService.signIn(memberFormDto));
        return loginMember;
    }

    @GetMapping(value = "/near-luggage")
    @ResponseBody
    public String getNearLuggage() {
        List<Order> luggageListInMaxDistance = luggageService.getLuggageListInMaxDistance(3.1235, 2.1235);

        return luggageListInMaxDistance.toString();
    }

}

