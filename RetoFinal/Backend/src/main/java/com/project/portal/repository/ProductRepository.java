package com.project.portal.repository;

import com.project.portal.entity.CustomerEntity;
import com.project.portal.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByCustomer (CustomerEntity customer);
    ProductEntity findByAccountNumber (String accountNumber);
}
