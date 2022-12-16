package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Cafe;
import com.sherpa.carrier_sherpa.domain.exception.BaseException;
import com.sherpa.carrier_sherpa.domain.exception.ErrorCode;
import com.sherpa.carrier_sherpa.domain.repository.CafeRepository;
import com.sherpa.carrier_sherpa.domain.repository.OrderRepository;
import com.sherpa.carrier_sherpa.dto.Cafe.CafeResDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CafeService {

    private final CafeRepository cafeRepository;
    private final OrderRepository orderRepository;

    public CafeService(CafeRepository cafeRepository, OrderRepository orderRepository) {
        this.cafeRepository = cafeRepository;
        this.orderRepository = orderRepository;
    }

    public List<CafeResDto> findByDistance(String memberId, Double lat, Double lng){
        List<Cafe> cafes = cafeRepository.findAll()
                .stream()
                .filter(cafe ->
                        DistanceService.getDistance(cafe.getLat(), cafe.getLng(), lat, lng)<1.001)
                .collect(Collectors.toList());

        return cafes.stream()
                .map(cafe -> CafeResDto.of(cafe))
                .collect(Collectors.toList());
    }

    public CafeResDto findById(String memberId, String cafeId){
        Cafe cafe = cafeRepository.findById(cafeId).orElseThrow(
                ()->new BaseException(
                        ErrorCode.NOT_ORDER,
                        "해당하는 카페가 존재하지 않습니다."
                )
        );

        return new CafeResDto().of(cafe);
    }

//    public CafeResDto entrust(String cafeId){
//        Optional<Cafe> cafe = cafeRepository.findById(cafeId);
//
//        cafe.get().entrust();
//
//        cafeRepository.save(cafe.get());
//        return CafeResDto.of(cafe.get());
//    }
}
