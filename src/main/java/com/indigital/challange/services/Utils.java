package com.indigital.challange.services;

import com.indigital.challange.repository.models.Client;
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

        List<Double> dist = new ArrayList<>();
        double sum = 0;
        double standardDeviation = 0;

        //media
        double average = getAverage(listAges);

        //calculo el cuadrado de la distancia a la media y hago la sumatoria
        for (int i=0; i<listAges.size(); i++) {
            dist.add( Math.pow( average - (Math.abs(listAges.get(i))), 2) );
            sum = dist.get(i);
        }

        //divido la sumaroria entre el numero total de datos, y saco la raiz cuadrada
        standardDeviation = Math.sqrt( (sum/listAges.size()) );
        return standardDeviation;
    }

    public static List<Integer> getAges(Iterable<Client> clienteList) {
        LocalDate currentDate = LocalDate.now();
        List<Integer> ages = new ArrayList<>();
        for(int i=0; i<IterableUtils.size(clienteList); i++) {
            ages.add(calculateAge((IterableUtils.get(clienteList, i).getDateBirth()), currentDate));
        }
        return ages;
    }

    public static String calcProbableDateOfDeath(Integer age) {

        if (age >= 73) return "Now!";

        //promedio mundial de esperanza de vida (fuente: wikipedia)
        int lifeExpectancy = 73;

        int plus = lifeExpectancy - age;
        LocalDate date = LocalDate.now();
        date = date.plusYears(plus);
        return date.toString();
    }
}
