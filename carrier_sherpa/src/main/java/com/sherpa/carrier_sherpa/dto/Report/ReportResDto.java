package com.sherpa.carrier_sherpa.dto.Report;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportResDto {

    private String reportId;
    private String reportType;

    public ReportResDto( String reportId , String reportType){
        this.reportId = reportId;
        this.reportType = reportType;
    }
}
