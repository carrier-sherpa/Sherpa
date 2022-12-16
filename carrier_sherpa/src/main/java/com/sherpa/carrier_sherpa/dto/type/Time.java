package com.sherpa.carrier_sherpa.dto.type;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Time {
    private String hour;
    private String minute;

    public Time(String hour, String minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Time stringToTime(String time) {
        String[] hourminute = time.split(":");
        this.hour = hourminute[0];
        this.minute = hourminute[1];
        return this;
    }
}
