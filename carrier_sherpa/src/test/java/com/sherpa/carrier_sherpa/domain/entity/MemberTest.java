package com.sherpa.carrier_sherpa.domain.entity;

import com.sherpa.carrier_sherpa.domain.enums.MemberRole;
import com.sherpa.carrier_sherpa.domain.repository.LuggageRepository;
import com.sherpa.carrier_sherpa.domain.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void test(){
        Member member = new Member(123L, "test", "test", MemberRole.USER);
        memberRepository.save(member);
        System.out.println(memberRepository.findByEmail("email").getPassword());
    }

}