package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.dto.type.Address;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistanceService {
    public static Double getDistance(Double lat, Double lng, Double lat2, Double lng2) {
        double theta = lng - lng2;
        double dist = Math.sin(deg2rad(lat))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist / 1000;
    }
    //10진수를 radian(라디안)으로 변환
    private static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }

    public static Double[] vector(Address v1, Address v2){
        Double[] vector = {v2.getLat()-v1.getLat(), v2.getLng()-v1.getLng()};
        return vector;
    }

    public static boolean vectorCross(Double[] v1, Double[] v2) {
        if (v1[0]*v2[0]+v1[1]*v2[1]>=0){
            return true;
        }
        return false;
    }

    public static Double orthogonalDistance(
            Address p,
            Address start,
            Address end){
        Double[] vector = vector(start,end);
        Double x = p.getLat();
        Double y = p.getLng();
        Double orthogonalX = start.getLat() + vector[0] * ((vector[0] * (x - start.getLat()) + vector[1] * (y - start.getLng())) /
                (Math.pow(vector[0], 2) + Math.pow(vector[1], 2)));
        Double orthogonalY = start.getLng() + vector[1] * ((vector[0] * (x - start.getLat()) + vector[1] * (y - start.getLng())) /
                (Math.pow(vector[0], 2) + Math.pow(vector[1], 2)));
        System.out.println("orthogonalX = " + orthogonalX);
        System.out.println("orthogonalY = " + orthogonalY);

        return Math.sqrt(Math.pow((orthogonalX - x), 2) + Math.pow((orthogonalY - y), 2));
    }
}
