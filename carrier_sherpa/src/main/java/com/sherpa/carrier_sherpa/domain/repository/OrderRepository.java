package com.sherpa.carrier_sherpa.domain.repository;

import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    Optional<Order> findById(String id);

    @Query(value ="select * " +
            "from orders as r " +
            "WHERE :traveler = r.traveler_id",nativeQuery = true)
    List<Order> findByTravelerId(@Param("traveler") String traveler);

    @Query(value ="select * " +
            "from orders as r " +
            "WHERE :deliever = r.deliever_id",nativeQuery = true)
    List<Order> findByDelieverId(@Param("deliever") String deliever);

}
