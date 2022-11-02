package com.sherpa.carrier_sherpa.Controller;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.service.LuggageService;
import com.sherpa.carrier_sherpa.dto.LuggageReqDto;
import com.sherpa.carrier_sherpa.dto.LuggageResDto;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/luggage")
public class LuggageController {

    private final LuggageService luggageService;

    public LuggageController(LuggageService luggageService) {
        this.luggageService = luggageService;
    }

    @GetMapping("")
    public Luggage findByMemberId(@PathVariable Long memberId){
        return luggageService.findByMemberId(memberId);
    }

    @PostMapping("")
    public String create(
//            HttpServletRequest request,
            @RequestParam("email") String testemail,
            @RequestBody LuggageReqDto luggageReqDto){
//        HttpSession session = request.getSession();
//        String email = (String) session.getAttribute("email");
        luggageService.create(testemail,luggageReqDto);
        return "OK";
    }

    @PatchMapping("/{id}")
    public String update(
            @PathVariable("id") Long luggageId,
            @RequestBody LuggageReqDto luggageReqDto){
        luggageService.update(luggageId,luggageReqDto);
        return null;
    }
    // User -> LuggaeController 로 접근
    // User 정보와 Luggage 정보로 Luggage 생성
    // Luggage 생성하려면 Member가 필요

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable("id") Long luggageId,
            @RequestBody LuggageReqDto luggageReqDto){
        luggageService.delete(luggageId);
        return null;
    }
}
