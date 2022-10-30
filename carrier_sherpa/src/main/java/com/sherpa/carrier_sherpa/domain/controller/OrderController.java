package com.sherpa.carrier_sherpa.domain.controller;

import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.service.OrderService;
import com.sherpa.carrier_sherpa.dto.OrderFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/orders")
@Controller
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/new")
    public String orderForm(Model model) {
        model.addAttribute("orderFromDto", new OrderFormDto());
        return "order/orderForm";
    }

    @PostMapping(value = "/new")
    public String orderForm(@Valid OrderFormDto orderFormDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "order/orderForm";
        }

        try {
            Order order = orderService.createOrder(orderFormDto);
            orderService.saveOrder(order);
        } catch (IllegalStateException e) {
            return "order/orderForm";
        }

        return "redirect:/";
    }

    @DeleteMapping(value = "/delete/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
    }
}
