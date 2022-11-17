package com.sherpa.carrier_sherpa.dto.Report;

import com.sherpa.carrier_sherpa.domain.enums.ReportType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportReqDto {

    private String reportType;
    private String content;
    private int tripScore;
}
