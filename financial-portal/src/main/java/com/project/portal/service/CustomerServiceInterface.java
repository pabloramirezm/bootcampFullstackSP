package com.project.portal.service;

import com.project.portal.entity.CustomerEntity;
import com.project.portal.model.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    List<CustomerEntity> findAll();

    CustomerEntity findById(final String id);

    CustomerEntity update (final Customer dto);

    CustomerEntity create(final Customer dto);



    void delete(final String id);
}
