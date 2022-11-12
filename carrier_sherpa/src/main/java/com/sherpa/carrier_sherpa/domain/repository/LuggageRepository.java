package com.sherpa.carrier_sherpa.domain.repository;

import com.sherpa.carrier_sherpa.domain.entity.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LuggageRepository extends JpaRepository<Luggage,String> {
    List<Luggage> findByOrderId(String OrderId);
    Luggage save(Luggage luggage);
}