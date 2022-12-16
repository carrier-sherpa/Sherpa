package com.sherpa.carrier_sherpa.dto.Cafe;

import com.sherpa.carrier_sherpa.domain.entity.Terminal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TerminalDto {

    private String name;

    private Double lat;

    private Double lng;

    public TerminalDto( Terminal terminal){
        this.name = terminal.getName();
        this.lat = terminal.getLat();
        this.lng = terminal.getLng();
    }
}
