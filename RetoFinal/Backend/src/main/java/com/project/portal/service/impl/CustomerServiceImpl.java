package com.project.portal.service.impl;

import com.project.portal.entity.CustomerEntity;
import com.project.portal.entity.ProductEntity;
import com.project.portal.model.Customer;
import com.project.portal.repository.CustomerRepository;
import com.project.portal.service.CustomerServiceInterface;
import com.project.portal.service.ProductServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerServiceInterface {

    private final CustomerRepository repository;
    private final ModelMapper modelMapper;
    private final ProductServiceInterface productService;


    @Autowired
    public CustomerServiceImpl(final CustomerRepository repository, final ModelMapper modelMapper, ProductServiceInterface productService){
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public List<CustomerEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public CustomerEntity findById(String id) {
        return repository.findByidNumber(id);
    }

    @Override
    public CustomerEntity update(Customer customer) {

        final CustomerEntity old = repository.findByidNumber(customer.getIdNumber());
        if(Objects.isNull(old)){
            return null;
        }
        old.setName(customer.getName());
        old.setEmail(customer.getEmail());
        old.setIdNumber(customer.getIdNumber());
        old.setDateOfBirth(customer.getDateOfBirth());
        old.setLastName(customer.getLastName());
        old.setTypeId(customer.getTypeId());
        old.setModificationDate(new Date());
        return repository.save(old);
    }

    @Override
    public CustomerEntity create(Customer dto) {
        final CustomerEntity newEntity = modelMapper.map(Objects.requireNonNull(dto, "Ocurrio un error al procesar el Body de la peticion"), CustomerEntity.class);
        newEntity.setCreationDate(new Date());
        newEntity.setRol("admin");
        newEntity.setModificationDate(new Date());
        return repository.save(newEntity);
    }

    @Override
    public String delete(String id) {
        final CustomerEntity old = repository.findByidNumber(id);
        if(Objects.isNull(old)){
            return "El cliente que intenta eliminar no existe";
        }

        if(clientWhitActiveProducts(id)){
            return "El cliente no puede ser eliminado hasta cancelar todas sus cuentas";
        }
        repository.delete(old);
        return "Cliente eliminado con exito";
    }

    private boolean clientWhitActiveProducts(String id) {
        List<ProductEntity> products = productService.findByCustomer(id);
        boolean variable = false;

        for (ProductEntity product: products){
            if(product.getStatus().equals("activa")){
                variable = true;
            }
        }

        return variable;
    }
}
