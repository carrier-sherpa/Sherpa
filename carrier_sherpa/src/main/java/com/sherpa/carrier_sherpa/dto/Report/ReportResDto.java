package com.sherpa.carrier_sherpa.dto.Report;

import com.sherpa.carrier_sherpa.domain.entity.Report;
import com.sherpa.carrier_sherpa.domain.enums.ReportType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportResDto {

    private String reportId;
    private ReportType reportType;

    public ReportResDto( String reportId , ReportType reportType){
        this.reportId = reportId;
        this.reportType = reportType;
    }

    public static ReportResDto of(Report report){
        return new ReportResDto(report.getId(), report.getReportType());
    }
}
