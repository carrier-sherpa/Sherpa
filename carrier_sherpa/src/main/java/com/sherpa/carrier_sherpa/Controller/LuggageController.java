package com.sherpa.carrier_sherpa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LuggageController {

    @GetMapping("luggage")
    public String[] getLuggage(){

        return new String[]{"luggage1", "luggage2"};
    }
}
