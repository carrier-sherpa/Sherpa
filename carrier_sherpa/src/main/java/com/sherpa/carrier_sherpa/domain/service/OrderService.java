package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.entity.Member;
import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.enums.LuggageStatus;
import com.sherpa.carrier_sherpa.domain.exception.BaseException;
import com.sherpa.carrier_sherpa.domain.exception.ErrorCode;
import com.sherpa.carrier_sherpa.domain.repository.MemberRepository;
import com.sherpa.carrier_sherpa.domain.repository.OrderRepository;
import com.sherpa.carrier_sherpa.dto.Luggage.LuggageReqDto;
import com.sherpa.carrier_sherpa.dto.Luggage.LuggageResDto;
import com.sherpa.carrier_sherpa.dto.Orders.DelieverReqDto;
import com.sherpa.carrier_sherpa.dto.Orders.OrderReqDto;
import com.sherpa.carrier_sherpa.dto.Orders.OrderResDto;
import com.sherpa.carrier_sherpa.dto.type.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<LuggageResDto> luggages = luggageService.findByOrderId(orderId);

        return new OrderResDto().of(order,luggages);
    }

    public List<OrderResDto> findByMemberId(String memberId){
        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()-> new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );

        // 한 사람이 여러 개의 order를 가질 수 없는 것을 전제. 만약 이럴 경우 오류 날 것.
        List<Order> orders = orderRepository.findByTravelerId(memberId);

        return orders.stream()
                .map(order -> OrderResDto.of(order,luggageService.findByOrderId(order.getId()) ))
                .collect(Collectors.toList());
        // NPE 발생 가능 , 오류 처리 필요할지도?
        // NPE 발생 X -> 아무것도 없는 애들도 그냥 빈 리스트로 출력

    }

    public List<OrderResDto> findByDistance(String memberId, DelieverReqDto memberReqDto){
//        List<Order> inStart = orderRepository.findAll()
//                .stream()
//                .filter(order ->
//                        DistanceService.getDistance(order.getStart_lat(),order.getStart_lng(), memberReqDto.getStart().getLat(), memberReqDto.getStart().getLng())<1.0001
//                        || DistanceService.getDistance(order.getStart_lat(),order.getStart_lng(), (memberReqDto.getStart().getLat()+memberReqDto.getEnd().getLat())/(double)2, (memberReqDto.getStart().getLng()+memberReqDto.getEnd().getLng())/(double)2)<1.0001)
//                .collect(Collectors.toList());

        List<Order> orders = new ArrayList<>();
        List<Order> inStart = orderRepository.findAll();
        for (Order order : inStart) {
            Address orderStart = new Address(order.getStart_lat(), order.getStart_lng());
            Address orderEnd = new Address(order.getEnd_lat(), order.getEnd_lng());

            if ( ( DistanceService.vectorCross(DistanceService.vector(orderStart,orderEnd),DistanceService.vector(memberReqDto.getStart(),memberReqDto.getEnd()))) &&
                    (DistanceService.orthogonalDistance(orderStart,memberReqDto.getStart(),memberReqDto.getEnd())<=0.5 &&
                     DistanceService.orthogonalDistance(orderEnd,memberReqDto.getStart(),memberReqDto.getEnd())<=0.5) &&
                    (DistanceService.vectorCross(DistanceService.vector(memberReqDto.getStart(),orderStart),DistanceService.vector(memberReqDto.getStart(),memberReqDto.getEnd())))){
                orders.add(order);
            }
        }

        return orders
                .stream()
                .map(order -> OrderResDto.of(order,luggageService.findByOrderId(order.getId()) ))
                .collect(Collectors.toList());
    }

    public OrderResDto create(String travelerId, OrderReqDto orderReqDto) {
        Member loginMember = memberRepository.findById(travelerId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );
        Order order = new Order(
                loginMember,
                null,
                orderReqDto.getStartTime().getHour()+":"+orderReqDto.getStartTime().getMinute(),
                orderReqDto.getEndTime().getHour()+":"+orderReqDto.getEndTime().getMinute(),
                orderReqDto.getStart(),
                orderReqDto.getEnd(),
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
        List<LuggageResDto> luggages = luggageService.findByOrderId(orderId);

        return new OrderResDto().of(order,luggages);

    }

    public OrderResDto accept(String memberId, String orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_ORDER,
                        "해당하는 서비스가 존재하지 않습니다."
                )
        );
        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );

        if (order.getTraveler().getId().equals(memberId)){
            throw new BaseException(
                    ErrorCode.NOT_AUTHORIZATION,
                    "Traveler와 Deliever가 동일합니다."
            );
        }

        if (order.getStatus()==LuggageStatus.ACCEPT){
            throw new BaseException(
                    ErrorCode.NOT_AUTHORIZATION,
                    "해당 서비스는 다른 Deliever가 존재합니다."
            );
        }
        order.accept(loginMember);
        orderRepository.save(order);
        // 유저에게 푸쉬메시지 날라가는 API도 필요할 듯

        List<LuggageResDto> luggages = luggageService.findByOrderId(orderId);

        return new OrderResDto().of(order,luggages);
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
                orderReqDto.getEnd(),
                orderReqDto.getStartTime().getHour()+":"+orderReqDto.getStartTime().getMinute(),
                orderReqDto.getEndTime().getHour()+":"+orderReqDto.getEndTime().getMinute(),
                orderReqDto.getLuggageImgUrl()
        );

        for (LuggageReqDto luggage : orderReqDto.getLuggages() ){
            luggageService.update(
                    loginMember.getId(),
                    orderId,
                    luggage
            );
        }

        //!!!!
//        return new OrderResDto().of(order); OrderResDto NPE 처리 필요
        return null;
    }

    public OrderResDto end(String memberId, String orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_ORDER,
                        "해당하는 서비스가 존재하지 않습니다."
                )
        );
        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );

        if (!order.getDeliever().getId().equals(memberId)){
            throw new BaseException(
                    ErrorCode.NOT_AUTHORIZATION,
                    "해당 서비스에 대한 권한이 없습니다."
            );
        }

        if (!(order.getStatus()==LuggageStatus.ACCEPT)){
            throw new BaseException(
                    ErrorCode.NOT_AUTHORIZATION,
                    "해당 서비스가 배송 상태가 아닙니다."
            );
        }
        order.close(loginMember,LuggageStatus.ARRIVE);
        orderRepository.save(order);

        List<LuggageResDto> luggages = luggageService.findByOrderId(orderId);

        return new OrderResDto().of(order,luggages);
    }

    @Transactional
    public OrderResDto close(String memberId, String orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_ORDER,
                        "해당하는 서비스가 존재하지 않습니다."
                )
        );

        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );

        if ((order.getStatus()==LuggageStatus.DELIVER
                || order.getStatus()==LuggageStatus.ARRIVE
                || order.getStatus()==LuggageStatus.CANCEL)){
            throw new BaseException(
                    ErrorCode.NOT_AUTHORIZATION,
                    "해당 서비스를 취소할 수 없습니다."
            );
        }

//        List<LuggageResDto> luggages = luggageService.findByOrderId(orderId);

        String travelerId = order.getTraveler().getId();

        if (memberId.equals(travelerId)) {
            orderRepository.delete(order);
            if ( order.getStatus().equals(LuggageStatus.ACCEPT)){
                loginMember.setTripEnergy(0);
                // - 상대방에게 푸시메시지!!
            }
            return new OrderResDto();
        }

        List<LuggageResDto> luggages = luggageService.findByOrderId(orderId);

        // Only Deliever만 넘어옴.
        order.close(null, LuggageStatus.REGISTER);
        loginMember.setTripEnergy(0); // - Travel Engergy 감소 및 제재
        // - 상대방에게 푸시메시지!!
        orderRepository.save(order);
        memberRepository.save(loginMember);
        // 유저에게 푸쉬메시지 날라가는 API도 필요할 듯

        return new OrderResDto().of(order,luggages);
    }

    @Transactional
    public OrderResDto delete(String memberId, String orderId) {
        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_ORDER,
                        "해당하는 서비스가 존재하지 않습니다."
                )
        );
        if(!loginMember.getId().equals(order.getTraveler().getId())){
            throw new BaseException(
                    ErrorCode.NOT_LUGGAGE,
                    "캐리어 변경 권한이 존재하지 않습니다."
            );
        }
        orderRepository.delete(order);

        return null;

        // 해당 Order에 Report가 있으면 delete가 안 됨.
    }
}
