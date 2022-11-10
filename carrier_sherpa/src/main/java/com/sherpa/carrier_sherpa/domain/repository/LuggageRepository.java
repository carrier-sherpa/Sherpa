package com.sherpa.carrier_sherpa.domain.repository;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import com.sherpa.carrier_sherpa.dto.LuggageResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LuggageRepository extends JpaRepository<Luggage,String> {
    List<Luggage> findByMember_Id(String email);

    List<Luggage> findByOrderId(String OrderId);
    Luggage save(Luggage luggage);
}