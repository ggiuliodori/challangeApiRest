package com.indigital.challange.api;

import com.indigital.challange.repository.models.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteController clienteController;

    @RequestMapping(value = "/creacliente)", method = RequestMethod.POST)
    public ResponseEntity<?> addCliente(@RequestBody Cliente jsonCliente) {
        try {
            clienteController.addCliente(jsonCliente);
            log.info("status code: {}", HttpStatus.CREATED);
            return new ResponseEntity<>(jsonCliente, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse jsonResponse = new ErrorResponse();
            jsonResponse.setTimestamp(new Date());
            jsonResponse.setStatus(HttpStatus.CONFLICT);
            jsonResponse.setMessage(e.getMessage());
            jsonResponse.setCode(409);
            jsonResponse.setError(e.getClass().getName());
            jsonResponse.setPath("/api/creacliente");
            log.error("error: {}", jsonResponse);
            return new ResponseEntity<>(jsonResponse, HttpStatus.CONFLICT);
        }
    }
}
