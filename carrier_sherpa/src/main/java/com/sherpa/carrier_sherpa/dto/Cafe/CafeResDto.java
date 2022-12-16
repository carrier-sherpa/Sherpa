package com.sherpa.carrier_sherpa.dto.Cafe;

import com.sherpa.carrier_sherpa.domain.entity.Cafe;
import com.sherpa.carrier_sherpa.dto.type.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeResDto {
    private String id;
    private String name;
    private Address address;

    private int restNum;

    // image 도 추후에 넘겨줘야할듯

    private String content;

    public CafeResDto(
            String id,
            String name,
            Address address,
            int restNum,
            String content
    ){
        this.id = id;
        this.name = name;
        this.address = address;
        this.restNum = restNum;
        this.content = content;
    }
    public static CafeResDto of(Cafe cafe){

        return new CafeResDto(
                cafe.getId(),
                cafe.getCafeName(),
                new Address(cafe.getLat(), cafe.getLng()),
                cafe.getLuggageNum(),
                cafe.getContent()
        );
    }
}
