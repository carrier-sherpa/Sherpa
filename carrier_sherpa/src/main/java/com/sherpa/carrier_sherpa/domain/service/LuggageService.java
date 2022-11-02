package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.entity.Member;
import com.sherpa.carrier_sherpa.domain.enums.LuggageType;
import com.sherpa.carrier_sherpa.domain.enums.MemberRole;
import com.sherpa.carrier_sherpa.domain.repository.LuggageRepository;
import com.sherpa.carrier_sherpa.domain.repository.MemberRepository;
import com.sherpa.carrier_sherpa.dto.LuggageReqDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LuggageService {

    private final LuggageRepository luggageRepository;
    private final MemberRepository memberRepository;


    public LuggageService(LuggageRepository luggageRepository, MemberRepository memberRepository) {
        this.luggageRepository = luggageRepository;
        this.memberRepository = memberRepository;
    }

    public Luggage findByMemberId(Long memberId){
        Luggage luggage = new Luggage();
        return luggageRepository.findByMember_Id(memberId).get();
    }

    public Luggage create(String email,LuggageReqDto luggageReqDto) {
        // 일단 유저 빼고 Luggage 등록하는 법부터 익히자
        // Member 확인을 위해 MmeberREpository를 쓴다? 불필요한데... 일단 하자!

        Member testMember = memberRepository.findByEmail(email);
        if (testMember==null) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        Luggage luggage = new Luggage(
                123L,
                testMember,
                "seoul",
                "kangwon",
                "2022.11.01",
                "2022.11.10",
                null,
                LuggageType.MEDIUM

        );
        return this.luggageRepository.save(luggage);
    }

    public Luggage update(Long luggageID, LuggageReqDto luggageReqDto){

        Member testMember = memberRepository.findByEmail("email");
        Luggage updateluggage = new Luggage(
                100L,
                testMember,
                "busan",
                "kangwon",
                "2022.11.01",
                "2022.11.10",
                null,
                LuggageType.SMALL
        );

        Optional<Luggage> luggage = luggageRepository.findById(luggageID);
        if (luggage==null){
            throw new IllegalStateException("존재하지 않는 캐리어입니다.");
        }
        luggage.get().update(
                testMember,
                "busan",
                "kangwon",
                "2022.11.01",
                "2022.11.10",
                null,
                LuggageType.SMALL
        );
        luggageRepository.save(luggage.get());
        return null;
    }

    public Luggage delete(Long luggageId){
        luggageRepository.deleteById(luggageId);
        return null;
    }

}