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

    public static int average(List<Integer> listAges) {
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

    public static int standardDeviation(Iterable<Cliente> clienteList) {
        return 0;
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
