package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.dto.type.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.DoubleBinaryOperator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DistanceServiceTest {


    @Test
    void orthogonalTest(){
        //give
        Address A = new Address(1.0, 0.0);
        Address B = new Address(5.0, 4.0);
        Address C = new Address(0.0, 5.0);

        //when

        //what 4.2426405
        System.out.println(DistanceService.orthogonalDistance(C, A, B));
        assertThat(DistanceService.orthogonalDistance(C, A, B)).isEqualTo(4.0);

    }

    @Test
    void positiveVector(){
        //given
        Address A = new Address(0.0, 0.0);
        Address B = new Address(5.0, 0.0);
        Address C = new Address(3.0, 0.0);
        Address D = new Address(-2.0, 5.0);

        Double[] v1 = DistanceService.vector(A,B);
//        Double[] v2 = DistanceService.vector(C[0], C[1], D[0], D[1]);
        Double[] v2 = DistanceService.vector(D,C);
        assertThat(DistanceService.vectorCross(v1, v2)).isEqualTo(true);
    }

    @Test
    void findByDistance(){
        //given
        Address A = new Address(1.0, 0.0);
        Address B = new Address(5.0, 0.0);
        Address C = new Address(2.0, 1.0);
        Address D = new Address(4.0, 1.0);

        // C가 D보다 A에 가까운 게 전제
        //when
        Double[] AB = DistanceService.vector(A,B);
        Double[] CD = DistanceService.vector(C, D);
        Double[] DC = DistanceService.vector(D, C);
        Double[] AC = DistanceService.vector(A, C);
        if (DistanceService.vectorCross(AB,DC)){
            System.out.println("positive vector!");
        }
        if ((DistanceService.orthogonalDistance(C, A, B) <=1 )
                && (DistanceService.orthogonalDistance(D, A, B)<=1)) {
            System.out.println("distance is ok");
        }
        if (DistanceService.vectorCross(AB,AC)){
            System.out.println("C,D is in squre space");
        }

        //what
    }

}