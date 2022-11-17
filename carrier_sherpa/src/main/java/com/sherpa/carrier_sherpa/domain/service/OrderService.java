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
        List<LuggageResDto> luggages = luggageService.findByOrderId(orderId);

        return new OrderResDto().of(order,luggages);
    }

    public List<LuggageResDto> findByMemberId(String memberId){
        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()-> new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );

        // 한 사람이 여러 개의 order를 가질 수 없는 것을 전제. 만약 이럴 경우 오류 날 것.
        Optional<Order> order = orderRepository.findById(memberId);
        List<LuggageResDto> luggages = luggageService.findByOrderId(order.get().getId());
        // NPE 발생 가능 , 오류 처리 필요할지도?
        // NPE 발생 X -> 아무것도 없는 애들도 그냥 빈 리스트로 출력

        return luggages;
    }

    public List<OrderResDto> findByDistance(String memberId, DelieverReqDto delieverReqDto){
        List<Order> inStart = orderRepository.findAll()
                .stream()
                .filter(order ->
                        getDistance(order.getStart_lat(),order.getStart_lng(), delieverReqDto.getStart().getLat(), delieverReqDto.getStart().getLng())<1.0001)
                .collect(Collectors.toList());

        List<Order> inEnd = inStart
                .stream()
                .filter(order ->
                        getDistance(order.getEnd_lat(),order.getEnd_lng(), delieverReqDto.getEnd().getLat(), delieverReqDto.getEnd().getLng())<1.0001)
                .collect(Collectors.toList());
        // Order -> OrderResDto( Order, List<Luggage> by OrderId )

        return inEnd
                .stream()
                .map(order -> OrderResDto.of(order,luggageService.findByOrderId(order.getId()) ))
                .collect(Collectors.toList());
    }

    public static Double getDistance(Double lat, Double lnt, Double lat2, Double lnt2) {
        double theta = lnt - lnt2;
        double dist = Math.sin(deg2rad(lat))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist / 1000;
    }
    //10진수를 radian(라디안)으로 변환
    private static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
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
                orderReqDto.getStartTime(),
                orderReqDto.getEndTime(),
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

        if (order.getTraveler().getId().equals(delieverId)){
            throw new BaseException(
                    ErrorCode.NOT_AUTHORIZATION,
                    "Traveler와 Deliever가 동일합니다."
            );
        }

        if (!(order.getDeliever()==null)){
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

        //!!!!
//        return new OrderResDto().of(order); OrderResDto NPE 처리 필요
        return null;
    }

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
    }
}
