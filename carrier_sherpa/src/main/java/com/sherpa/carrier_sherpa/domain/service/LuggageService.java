package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.domain.entity.Member;
import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.enums.LuggageType;
import com.sherpa.carrier_sherpa.domain.exception.BaseException;
import com.sherpa.carrier_sherpa.domain.exception.ErrorCode;
import com.sherpa.carrier_sherpa.domain.repository.LuggageRepository;
import com.sherpa.carrier_sherpa.domain.repository.MemberRepository;
import com.sherpa.carrier_sherpa.domain.repository.OrderRepository;
import com.sherpa.carrier_sherpa.dto.LuggageReqDto;
import com.sherpa.carrier_sherpa.dto.LuggageResDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LuggageService {

    private final LuggageRepository luggageRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;


    public LuggageService(LuggageRepository luggageRepository, MemberRepository memberRepository, OrderRepository orderRepository) {
        this.luggageRepository = luggageRepository;
        this.memberRepository = memberRepository;
        this.orderRepository = orderRepository;
    }

    public List<LuggageResDto> findByMemberId(String id){
        List<Luggage> luggages =luggageRepository.findByMember_Id(id);
        // NPE 발생 가능 , 오류 처리 필요할지도?
        // NPE 발생 X -> 아무것도 없는 애들도 그냥 빈 리스트로 출력
        for ( Luggage luggage : luggages){
            System.out.println("luggage id : "+luggage.getId());
        }
        return luggages.stream()
                .map(luggage -> new LuggageResDto(luggage))
                .collect(Collectors.toList());
    }

    public Luggage create(String memberId,String orderId, LuggageReqDto luggageReqDto) {
        // Member 확인을 위해 MmeberREpository를 쓴다? 불필요해보이지만...
        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()-> new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );
        Order order = orderRepository.findById(orderId).orElseThrow(
                ()-> new BaseException(
                        ErrorCode.NOT_LUGGAGE,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );

        System.out.println("maybe here");
        System.out.println("test : "+luggageReqDto.getSize());
        Luggage luggage = new Luggage(
                loginMember,
                order,
                LuggageType.valueOf(luggageReqDto.getSize()),
                luggageReqDto.getNum()
        );
        return this.luggageRepository.save(luggage);
    }

    public List<Luggage> update( String memberId, String orderId, LuggageReqDto luggageReqDto){

        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()-> new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );
        // 아마 리스트형식
        List<Luggage> luggages = luggageRepository.findByOrderId(orderId);



        for ( Luggage luggage : luggages){
            if ( luggage==null ){
                new BaseException(
                        ErrorCode.NOT_LUGGAGE,
                        "해당하는 짐이 존재하지 않습니다."
                );
            }

            if (!luggage.getMember().getId().equals(memberId)) {
                throw new BaseException(
                        ErrorCode.NOT_LUGGAGE,
                        "캐리어 변경 권한이 존재하지 않습니다."
                );
            }

            luggage.update(
                    LuggageType.valueOf(luggageReqDto.getSize()),
                    luggageReqDto.getNum()
            );

            luggageRepository.save(luggage);
        }

        //Order 단위에서 어떤 값들을 return 해줘야 할지는 나중에 정하자.
        return null;
    }

    @Transactional
    public Luggage delete(String luggageId,String memberId){
        // causw에는 왜 throw하는 문법이 보이질 않지, @ExceptionHandler
        Member loginMember = memberRepository.findById(memberId).orElseThrow(
                ()-> new BaseException(
                        ErrorCode.NOT_USER,
                        "해당하는 유저가 존재하지 않습니다."
                )
        );
        Luggage luggage = luggageRepository.findById(luggageId).orElseThrow(
                ()-> new BaseException(
                ErrorCode.NOT_LUGGAGE,
                "해당하는 짐이 존재하지 않습니다."
        )
        );
        // 이미 삭제되 경우 validation 추가 -> 존재하지 않는 캐리어와 이미 삭제한 캐리어 라는 중복되는 validation
        if (!luggage.getMember().getId().equals(memberId)) {
            throw new BaseException(
                    ErrorCode.NOT_LUGGAGE,
                    "캐리어 변경 권한이 존재하지 않습니다."
            );
        }
        // 완전 삭제는 아니고 일단 구현. 추후 refactoring
        //물갈이중 오류
//        if (luggage.getStatus().equals(LuggageStatus.INACTIVE)) {
//            throw new BaseException(
//                    ErrorCode.NOT_LUGGAGE,
//                    "이미 삭제된 캐리어입니다."
//            );
//        }
//        luggage.setStatus(LuggageStatus.INACTIVE);
        return luggageRepository.save(luggage);
    }

}