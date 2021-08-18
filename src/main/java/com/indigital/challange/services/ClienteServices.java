package com.indigital.challange.services;

import com.google.common.collect.Lists;
import com.indigital.challange.repository.ClienteRepository;
import com.indigital.challange.repository.models.Client;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    @ExceptionHandler
    public void addCliente(Client cliente) throws ErrorService {
        LocalDate currentDate = LocalDate.now();
        try {
            cliente.setAge(Utils.calculateAge(cliente.getDateBirth(), currentDate));
            clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new ErrorService("Invalid client");
        }
    }

    public Iterable<Client> getAllClients() {
        log.info("Getting all clients");
        return clienteRepository.findAll();
    }

    public static List<JSONObject> bundleResponse(Iterable<Client> clienteList) {
        List<Client> listClients = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> bundle = new ArrayList<>();
        listClients = Lists.newArrayList(clienteList);
        for (int i=0; i<listClients.size(); i++) {
            jsonObject.put("name", listClients.get(i).getName());
            jsonObject.put("lastName", listClients.get(i).getLastName());
            jsonObject.put("age", listClients.get(i).getAge());
            jsonObject.put("dateBirth", listClients.get(i).getDateBirth());
            jsonObject.put("probableDateOfDeath", Utils.calcProbableDateOfDeath(listClients.get(i).getAge()));
            bundle.add(jsonObject);
        }
        return bundle;
    }

}
