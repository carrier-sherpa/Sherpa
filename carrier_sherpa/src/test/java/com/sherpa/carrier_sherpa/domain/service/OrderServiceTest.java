package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.entity.Member;
import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.enums.LuggageStatus;
import com.sherpa.carrier_sherpa.domain.enums.LuggageType;
import com.sherpa.carrier_sherpa.domain.enums.MemberRole;
import com.sherpa.carrier_sherpa.domain.repository.LuggageRepository;
import com.sherpa.carrier_sherpa.domain.repository.MemberRepository;
import com.sherpa.carrier_sherpa.domain.repository.OrderRepository;
import com.sherpa.carrier_sherpa.dto.Orders.DelieverReqDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    OrderService orderService;
    @Mock
    MemberRepository memberRepository;
    @Mock
    OrderRepository orderRepository;
    @Mock
    LuggageRepository luggageRepository;

    private Member member = Member.builder()
            .email("test@naver.com")
            .password("test")
            .role(MemberRole.USER)
            .tripEnergy(23)
            .delieverTime(0)
            .build();
    private DelieverReqDto delieverReqDto = new DelieverReqDto(0.0, 0.0, 5.0, 0.0);
    @Test
    void givenAddressfindByDistance() {
        //given
        orderService.findByDistance(member.getId(), delieverReqDto);
        //when

        //then
    }
}