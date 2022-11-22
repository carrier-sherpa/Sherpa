package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Cafe;
import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.repository.CafeRepository;
import com.sherpa.carrier_sherpa.dto.Cafe.CafeReqDto;
import com.sherpa.carrier_sherpa.dto.Cafe.CafeResDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CafeService {

    private final CafeRepository cafeRepository;

    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
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
}
