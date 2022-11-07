package com.sherpa.carrier_sherpa.Controller;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.entity.Member;
import com.sherpa.carrier_sherpa.domain.service.LuggageService;
import com.sherpa.carrier_sherpa.domain.service.MemberService;
import com.sherpa.carrier_sherpa.dto.MemberFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final LuggageService luggageService;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @GetMapping(value = "/store")
    public String store() {
        memberService.saveMember(
                Member.builder()
                        .email("test@naver.com")
                        .password("1111")
                        .build());
        return "";
    }

    @PostMapping(value = "/new")
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            Member member = memberService.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            //TODO: 여기에 에러 났다는 걸 알려주는 구문
            return "member/memberForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/near-luggage")
    @ResponseBody
    public String getNearLuggage() {
        List<Luggage> luggageListInMaxDistance = luggageService.getLuggageListInMaxDistance(3.1235, 2.1235);

        return luggageListInMaxDistance.toString();
    }

}

