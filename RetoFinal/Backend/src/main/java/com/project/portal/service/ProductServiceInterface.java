package com.project.portal.service;

import com.project.portal.entity.CustomerEntity;
import com.project.portal.entity.ProductEntity;
import com.project.portal.model.Customer;
import com.project.portal.model.Product;

import java.util.List;

public interface ProductServiceInterface {

    List<ProductEntity> findByCustomer(String clientId);

    ProductEntity create(final Product dto, final String customerId);

    String inactiveProduct(final String productId);
    String activeProduct(final String productId);

    String delete(final String id);
}
