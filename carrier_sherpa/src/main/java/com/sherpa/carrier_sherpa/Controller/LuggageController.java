package com.sherpa.carrier_sherpa.Controller;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.service.LuggageService;
import com.sherpa.carrier_sherpa.dto.Luggage.LuggageReqDto;
import com.sherpa.carrier_sherpa.dto.Luggage.LuggageResDto;
import com.sherpa.carrier_sherpa.dto.Member.MemberResDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/luggage")
public class LuggageController {

    private final LuggageService luggageService;

    public LuggageController(LuggageService luggageService) {
        this.luggageService = luggageService;
    }

    // 추가 구현할 API : findInBlock,
    @GetMapping("")
    public List<LuggageResDto> findMyluggage(
            HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return null;
    }

    @PostMapping("")
    public Luggage create(
            HttpServletRequest httpServletRequest,
            @RequestBody LuggageReqDto luggageReqDto){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        return null;
    }

    @PatchMapping("/{id}")
    public String update(
            HttpServletRequest httpServletRequest,
            @PathVariable("id") String luggageId,
            @RequestBody LuggageReqDto luggageReqDto){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        luggageService.update(luggageId,memberResDto.getId(),luggageReqDto);
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(
            HttpServletRequest httpServletRequest,
            @PathVariable("id") String luggageId){
        HttpSession httpSession = httpServletRequest.getSession();
        MemberResDto memberResDto = (MemberResDto) httpSession.getAttribute("loginMember");
        luggageService.delete(luggageId,memberResDto.getId());
        return null;
    }
}
