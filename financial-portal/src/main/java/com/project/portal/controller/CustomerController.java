package com.project.portal.controller;

import com.project.portal.entity.CustomerEntity;
import com.project.portal.model.Customer;
import com.project.portal.service.CustomerServiceInterface;
import com.project.portal.util.DataConversionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "customer", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class CustomerController {

    private final CustomerServiceInterface queryService;
    private final ModelMapper modelMapper;
    private final DataConversionUtil dataConversion;

    @Autowired
    public CustomerController(final CustomerServiceInterface queryService, final ModelMapper modelMapper, DataConversionUtil dataConversion) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.dataConversion = dataConversion;
    }

    @GetMapping
    public List<Customer> findAll() {
        return queryService.findAll()
                .parallelStream()
                .map(entity -> modelMapper.map(entity, Customer.class))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<String> findById(@RequestParam("id") final String id) {
        try {
            final CustomerEntity entity = queryService.findById(id);
            if(Objects.isNull(entity)){
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("El usuario que trata de consultar no existe");
            }
            return buildResponse(entity);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }

    }


    @PostMapping
    public ResponseEntity<String> create(@RequestBody final Customer customer) {
        try {
            if(notIsAdult(customer.getDateOfBirth())){
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("El usuario debe ser mayor de edad");
            }
            final CustomerEntity created = queryService.create(customer);
            return buildResponse(created);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }




    @PutMapping
    public ResponseEntity<String> update(@RequestBody final Customer customer) {
        try {
            final CustomerEntity updated = queryService.update(customer);
            if(Objects.isNull(updated)){
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("El usuario que trata de modificar no existe");
            }
            return buildResponse(updated);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity delete(@RequestParam("id") final String id) {

        try {
            queryService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("Usuario eliminado con exito");
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }

    }

    private ResponseEntity<String> buildResponse(final CustomerEntity entity) {
        final String jsonResponse = dataConversion.dtoToJson(modelMapper.map(entity, Customer.class));
        return ResponseEntity.ok(jsonResponse);
    }

    private boolean notIsAdult(Date dateOfBirth) {
        Calendar age = Calendar.getInstance();
        age.setTime(dateOfBirth);

        Calendar compareCalendar = Calendar.getInstance();
        if(compareCalendar.get(Calendar.YEAR) - age.get(Calendar.YEAR) < 18){
            return true;
        }
        return false;
    }
}
