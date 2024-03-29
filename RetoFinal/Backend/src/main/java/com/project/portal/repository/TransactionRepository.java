package com.project.portal.repository;

import com.project.portal.entity.CustomerEntity;
import com.project.portal.entity.ProductEntity;
import com.project.portal.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByProduct (ProductEntity product);

}
