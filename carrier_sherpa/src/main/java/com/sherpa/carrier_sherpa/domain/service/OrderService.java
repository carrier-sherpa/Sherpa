package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.entity.Member;
import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.enums.LuggageStatus;
import com.sherpa.carrier_sherpa.domain.enums.LuggageType;
import com.sherpa.carrier_sherpa.domain.exception.BaseException;
import com.sherpa.carrier_sherpa.domain.exception.ErrorCode;
import com.sherpa.carrier_sherpa.domain.repository.LuggageRepository;
import com.sherpa.carrier_sherpa.domain.repository.MemberRepository;
import com.sherpa.carrier_sherpa.domain.repository.OrderRepository;
import com.sherpa.carrier_sherpa.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final LuggageService luggageService;

    public OrderResDto findById(String memberId, String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_ORDER,
                        "해당하는 서비스가 존재하지 않습니다."
                )
        );
        OrderResDto orderResDto = new OrderResDto();
        orderResDto.of(order);
        return orderResDto;
    }

    public List<LuggageResDto> findByMemberId(String memberId){
        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()-> new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );
        List<LuggageResDto> luggages = luggageService.findByMemberId(memberId);

        return luggages;
    }
    public OrderResDto create(String travelerId, OrderReqDto orderReqDto) {
        Member loginMember = memberRepository.findById(travelerId).get();
        Order order = new Order(
                loginMember,
                null,
                orderReqDto.getStart(),
                orderReqDto.getDestination(),
                orderReqDto.getStartTime(),
                orderReqDto.getEndTime(),
                orderReqDto.getLuggageImgUrl(),
                LuggageStatus.REGISTER
        );

        String orderId = orderRepository.save(order).getId();

        for (LuggageReqDto luggage : orderReqDto.getLuggages() ){
            luggageService.create(
                    loginMember.getId(),
                    orderId,
                    luggage
                    );
        }

        return null;
    }

    public OrderResDto accept(String delieverId, String orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_ORDER,
                        "해당하는 서비스가 존재하지 않습니다."
                )
        );
        Member loginMember = memberRepository.findById(delieverId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );
        if (order.getTraveler().equals(delieverId)){
            throw new BaseException(
                    ErrorCode.NOT_AUTHORIZATION,
                    "Traveler와 Deliever가 동일합니다."
            );
        }

        order.accept(loginMember);
        orderRepository.save(order);
        // 유저에게 푸쉬메시지 날라가는 API도 필요할 듯

        return new OrderResDto().of(order);

    }

    public OrderResDto update(String travelerId, String orderId, OrderReqDto orderReqDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_ORDER,
                        "해당하는 서비스가 존재하지 않습니다."
                )
        );
        System.out.println("travelerId = " + travelerId + ", orderId = " + orderId );
        Member loginMember = memberRepository.findById(travelerId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );
        //수정 권한 확인
        if ( !travelerId.equals(loginMember.getId())){
            throw new BaseException(
                    ErrorCode.NOT_AUTHORIZATION,
                    "서비스 변경 권한이 존재하지 않습니다."
            );
        }

        order.update(
                orderReqDto.getStart(),
                orderReqDto.getDestination(),
                orderReqDto.getStartTime(),
                orderReqDto.getEndTime(),
                orderReqDto.getLuggageImgUrl()
        );

        for (LuggageReqDto luggage : orderReqDto.getLuggages() ){
            luggageService.update(
                    loginMember.getId(),
                    orderId,
                    luggage
            );
        }

        return new OrderResDto().of(order);
    }
}
