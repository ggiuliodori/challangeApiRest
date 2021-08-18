package com.indigital.challange.services;

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
        List<JSONObject> response = new ArrayList<>();
        JSONObject bundle = new JSONObject();
        for (int i = 0; i< IterableUtils.size(clienteList); i++) {
            log.info(IterableUtils.get(clienteList, i).toString());
            bundle.put("name", IterableUtils.get(clienteList, i).getName());
            bundle.put("lastName", IterableUtils.get(clienteList, i).getLastName());
            bundle.put("age", IterableUtils.get(clienteList, i).getAge());
            bundle.put("dateBirth", IterableUtils.get(clienteList, i).getDateBirth());
            bundle.put("probableDateOfDeath", Utils.calcProbableDateOfDeath(IterableUtils.get(clienteList, i).getAge()));
            response.add(i, bundle);
            log.info("response " + response);
        }
        return response;
    }

}
