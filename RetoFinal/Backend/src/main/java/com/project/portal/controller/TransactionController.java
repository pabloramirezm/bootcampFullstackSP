package com.project.portal.controller;

import com.project.portal.entity.ProductEntity;
import com.project.portal.entity.TransactionEntity;
import com.project.portal.entity.TransactionEntity;
import com.project.portal.model.Product;
import com.project.portal.model.Transaction;
import com.project.portal.model.Transaction;
import com.project.portal.service.TransactionServiceInterface;
import com.project.portal.util.DataConversionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "transaction", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class TransactionController {

    private final TransactionServiceInterface queryService;
    private final ModelMapper modelMapper;
    private final DataConversionUtil dataConversion;

    @Autowired
    public TransactionController(final TransactionServiceInterface queryService, final ModelMapper modelMapper, DataConversionUtil dataConversion) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.dataConversion = dataConversion;
    }


    @PostMapping(params = {"id"})
    public ResponseEntity<String> create(@RequestParam("id") final String productNumber, @RequestBody final Transaction transaction) {
        try {
            final String message = queryService.create(transaction,productNumber);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(message);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }


    @PostMapping(params = {"destinationAccount","rootAccount"})
    public ResponseEntity<String> transfer(@RequestParam("destinationAccount") final String destinationAccount, @RequestParam("rootAccount") final String rootAccount, @RequestBody final Transaction transaction) {
        try {
            final String message = queryService.transfer(destinationAccount,rootAccount,transaction);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(message);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<List<Transaction>> findByProduct(@RequestParam("id") final String id) {
        try {
            return buildResponseList(queryService.findByProduct(id));

        } catch (final Exception ex) {
            System.out.println("Error");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ArrayList<>());
        }

    }

    private ResponseEntity<String> buildResponse(final TransactionEntity entity) {
        final String jsonResponse = dataConversion.dtoToJson(modelMapper.map(entity, Transaction.class));
        return ResponseEntity.ok(jsonResponse);
    }

    private ResponseEntity<List<Transaction>> buildResponseList(final List<TransactionEntity> entityList) {
        List<Transaction> response = entityList
                .parallelStream()
                .map(entity -> modelMapper.map(entity, Transaction.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
