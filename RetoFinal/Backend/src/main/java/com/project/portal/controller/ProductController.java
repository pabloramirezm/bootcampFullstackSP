package com.project.portal.controller;

import com.project.portal.entity.CustomerEntity;
import com.project.portal.entity.ProductEntity;
import com.project.portal.model.Customer;
import com.project.portal.model.Product;
import com.project.portal.service.ProductServiceInterface;
import com.project.portal.util.DataConversionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "product", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ProductController {


    private final ProductServiceInterface queryService;
    private final ModelMapper modelMapper;
    private final DataConversionUtil dataConversion;

    @Autowired
    public ProductController(final ProductServiceInterface queryService, final ModelMapper modelMapper, DataConversionUtil dataConversion) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.dataConversion = dataConversion;
    }

    @PutMapping(path = "/inactive" , params = {"id"})
    public ResponseEntity<String> inactiveProduct(@RequestParam("id") final String productId) {
        try {
            final String cancelProduct = queryService.inactiveProduct(productId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(cancelProduct);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }

    @PutMapping(path = "/active" , params = {"id"})
    public ResponseEntity<String> activeProduct(@RequestParam("id") final String productId) {
        try {
            final String activeProduct = queryService.activeProduct(productId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(activeProduct);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }

    @PostMapping(params = {"id"})
    public ResponseEntity<String> create(@RequestParam("id") final String idClient, @RequestBody final Product product) {
        try {
            if(product.getBalance() < 0.0){
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("La cuenta no puede crearse con un saldo menor a 0");
            }
            final ProductEntity created = queryService.create(product,idClient);
            return buildResponse(created);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }




    @DeleteMapping(params = {"id"})
    public ResponseEntity<String> delete(@RequestParam("id") final String id) {

        try {
            String message = queryService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(message);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }

    }

    @GetMapping(params = {"customerId"})
    public ResponseEntity<List<Product>> findByCustomerId(@RequestParam("customerId") final String id) {
        try {
            return buildResponseList(queryService.findByCustomer(id));

        } catch (final Exception ex) {
            System.out.println("Error ".concat(ex.getMessage()));
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ArrayList<>());
        }

    }

    private ResponseEntity<String> buildResponse(final ProductEntity entity) {
        final String jsonResponse = dataConversion.dtoToJson(modelMapper.map(entity, Product.class));
        return ResponseEntity.ok(jsonResponse);
    }

    private ResponseEntity<List<Product>> buildResponseList(final List<ProductEntity> entityList) {
        List<Product> response = entityList
                .parallelStream()
                .map(entity -> modelMapper.map(entity, Product.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }



}
