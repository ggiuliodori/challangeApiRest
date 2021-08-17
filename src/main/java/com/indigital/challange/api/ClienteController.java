package com.indigital.challange.api;

import com.indigital.challange.repository.models.Cliente;
import com.indigital.challange.services.ClienteServices;
import com.indigital.challange.services.ErrorService;
import com.indigital.challange.services.Utils;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static com.indigital.challange.services.Utils.getStandardDeviation;

@Slf4j
@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @RequestMapping(value = "/creacliente", method = RequestMethod.POST)
    public ResponseEntity<?> addCliente(@RequestBody Cliente jsonCliente) {
        log.info("inside controller");
        try {
            clienteServices.addCliente(jsonCliente);
            log.info("status code: {}", HttpStatus.CREATED);
            return new ResponseEntity<>(jsonCliente, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException | ErrorService e) {
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

    @RequestMapping(value = "/kpideclientes", method = RequestMethod.GET)
    public ResponseEntity<?> calculateKpide() {
        JSONObject response = new JSONObject();
        Iterable<Cliente> clienteList = clienteServices.getAllDevice();

        List<Integer> average = Utils.getAges(clienteList);

        response.put("average", Utils.getAverage(average));
        response.put("standardDeviation", getStandardDeviation(average));

        log.info("status code: {}", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/listclientes", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Cliente>> getAllDevice() {
        Iterable<Cliente> clienteList = clienteServices.getAllDevice();
        log.info("status code: {}", HttpStatus.OK);
        return new ResponseEntity<Iterable<Cliente>>(clienteList, HttpStatus.OK);
    }
}
