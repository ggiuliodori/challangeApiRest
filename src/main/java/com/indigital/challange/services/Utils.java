package com.indigital.challange.services;

import com.indigital.challange.repository.models.Cliente;
import org.apache.commons.collections4.IterableUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }

    public static int getAverage(List<Integer> listAges) {
        int sum = 0;
        int count = 0;
        for (int i=0; i<listAges.size(); i++){
            sum = sum + listAges.get(i);
            count ++;
        }
        if (count == 0) {
            return 0;
        }
        return (sum / count);
    }

    public static double getStandardDeviation(List<Integer> listAges) {
        double average = getAverage(listAges);
        List<Double> dist = new ArrayList<>();
        double sum = 0;
        double standardDeviation = 0;

        for (int i=0; i<listAges.size(); i++) {
            dist.add( Math.pow( average - (Math.abs(listAges.get(i))), 2) );
            sum = dist.get(i);
        }
        standardDeviation = Math.sqrt( (sum/listAges.size()) );
        return standardDeviation;
    }

    public static List<Integer> getAges(Iterable<Cliente> clienteList) {
        LocalDate currentDate = LocalDate.now();
        List<Integer> ages = new ArrayList<>();
        for(int i=0; i<IterableUtils.size(clienteList); i++) {
            ages.add(calculateAge((IterableUtils.get(clienteList, i).getDateBirth()), currentDate));
        }
        return ages;
    }
}
