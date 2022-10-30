package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.repository.OrderRepository;
import com.sherpa.carrier_sherpa.dto.OrderFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public OrderFormDto createOrderFormDto(Order order) {
        return OrderFormDto.builder().orderId(order.getId()).build();
    }

    public Order createOrder(OrderFormDto orderFormDto) {
        return new Order();
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

//    public OrderFormDto findOrder(OrderFormDto orderFormDto) {
//        Order findOrder = orderRepository.findById(orderFormDto.getOrderId())
//                .orElseThrow(() -> new IllegalStateException("해당하는 order가 존재하지 않습니다."));
//
//        return createOrderFormDto(findOrder);
//    }
}
