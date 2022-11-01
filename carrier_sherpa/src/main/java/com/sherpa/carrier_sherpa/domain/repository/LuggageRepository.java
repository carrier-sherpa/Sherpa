package com.sherpa.carrier_sherpa.domain.repository;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.dto.LuggageResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LuggageRepository extends JpaRepository<Luggage,Long> {
    Optional<Luggage> findByMember_Id(Long member_Id);
    Luggage save(Luggage luggage);
}

