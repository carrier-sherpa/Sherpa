package com.sherpa.carrier_sherpa.domain.entity;

import com.sherpa.carrier_sherpa.domain.enums.ReportType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Table(name = "report")
@Entity
public class Report  extends BaseEntity{

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    private ReportType reportType;

    private String content;

    @Builder
    public Report( Order order, ReportType reportType, String content) {
        this.order = order;
        this.reportType = reportType;
        this.content = content;
    }

    public Report( String id, Order order, ReportType reportType, String content) {
        super(id);
        this.order = order;
        this.reportType = reportType;
        this.content = content;
    }

}
