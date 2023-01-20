package com.project.portal.service;

import com.project.portal.entity.TransactionEntity;
import com.project.portal.model.Transaction;

import java.util.List;

public interface TransactionServiceInterface {
    List<TransactionEntity> findByProduct(final String id);

    String create(final Transaction transaction,final String productNumber);

    String transfer(String destinationAccount, String rootAccount, Transaction transaction);
}
