package com.project.portal.service.impl;

import com.project.portal.entity.CustomerEntity;
import com.project.portal.model.Customer;
import com.project.portal.repository.CustomerRepository;
import com.project.portal.service.CustomerServiceInterface;
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


    @Autowired
    public CustomerServiceImpl(final CustomerRepository repository, final ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.repository = repository;

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
        newEntity.setModificationDate(new Date());
        return repository.save(newEntity);
    }

    @Override
    public void delete(String id) {
        final CustomerEntity old = repository.findByidNumber(id);
        if(Objects.isNull(old)){

        }
        repository.delete(old);
    }
}
