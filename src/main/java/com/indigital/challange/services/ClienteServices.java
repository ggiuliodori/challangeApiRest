package com.indigital.challange.services;

import com.indigital.challange.repository.ClienteRepository;
import com.indigital.challange.repository.models.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Service
@Slf4j
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    @ExceptionHandler
    public void addCliente(Cliente cliente) throws ErrorService {
        LocalDate currentDate = LocalDate.now();
        try {
            cliente.setAge(calculateAge(cliente.getDateBirth(), currentDate));
            clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new ErrorService("Invalid client");
        }
    }

    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }

    public Iterable<Cliente> getAllDevice() {
        log.info("Getting all devices");
        return clienteRepository.findAll();
    }
}
