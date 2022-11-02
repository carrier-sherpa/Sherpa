package com.sherpa.carrier_sherpa.domain.entity;

import com.sherpa.carrier_sherpa.domain.enums.LuggageType;
import com.sherpa.carrier_sherpa.domain.enums.MemberRole;
import com.sherpa.carrier_sherpa.domain.repository.LuggageRepository;
import com.sherpa.carrier_sherpa.domain.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LuggageTest {

    @Autowired
    LuggageRepository luggageRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void test(){
        Member testMember = memberRepository.findByEmail("email");
        System.out.println(testMember.getRole());
        Luggage luggage = new Luggage(
                123L,
                testMember,
                "seoul",
                "incheon",
                "2022.11.01",
                "2022.11.10",
                null,
                LuggageType.MEDIUM

        );

        luggageRepository.save(luggage);



    }

}