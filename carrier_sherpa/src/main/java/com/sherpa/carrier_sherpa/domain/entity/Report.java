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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "reporter_id")
    private Member reporter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "reported_id")
    private Member reported;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    private ReportType reportType;

    private String content;

    private int tripScore;
    @Builder
    public Report( Member reporter,Member reported, Order order, ReportType reportType, String content, int tripScore) {
        this.reporter = reporter;
        this.reported = reported;
        this.order = order;
        this.reportType = reportType;
        this.content = content;
        this.tripScore= tripScore;
    }

    public Report( Member reporter,Member reported,String id, Order order, ReportType reportType, String content) {
        super(id);
        this.reporter = reporter;
        this.reported = reported;
        this.order = order;
        this.reportType = reportType;
        this.content = content;
    }

}
