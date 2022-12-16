package com.sherpa.carrier_sherpa.domain.repository;

import com.sherpa.carrier_sherpa.domain.entity.Member;
import com.sherpa.carrier_sherpa.domain.entity.Order;
import com.sherpa.carrier_sherpa.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, String> {

    Report findByOrderId(Long orderId);

    @Query(value ="select * " +
            "from report " +
            "where :reporter = report.reporter_id",nativeQuery = true)
    List<Report> findByReporter(@Param("reporter") String reporter);

    @Query(value ="select * " +
            "from report as r " +
            "WHERE :reported =r.reported_id",nativeQuery = true)
    List<Report> findByReported(@Param("reported") String reported);
}