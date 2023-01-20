package com.project.portal.service.impl;

import com.project.portal.entity.ProductEntity;
import com.project.portal.entity.TransactionEntity;
import com.project.portal.model.Transaction;
import com.project.portal.repository.ProductRepository;
import com.project.portal.repository.TransactionRepository;
import com.project.portal.service.TransactionServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionServiceInterface {

    private final TransactionRepository repository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public TransactionServiceImpl(TransactionRepository repository, ProductRepository productRepository, final ModelMapper modelMapper){
        this.repository = repository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;

    }
    @Override
    public List<TransactionEntity> findByProduct(String id) {
        ProductEntity product = productRepository.findByAccountNumber(id);
        return repository.findByProduct(product);
    }

    @Override
    public String create(Transaction dto, String productNumber) {
        final TransactionEntity newEntity = modelMapper.map(Objects.requireNonNull(dto, "Ocurrio un error al procesar el Body de la peticion"), TransactionEntity.class);
        ProductEntity productEntity = productRepository.findByAccountNumber(productNumber);
        if (Objects.isNull(productEntity)){
            return "La cuenta con la que quiere hacer la transaccion no existe";
        }

        if(newEntity.getMovementType().equals("C")){
            if(productEntity.getStatus().equals("inactiva")){
                return "No se puede realizar una consignación a una cuenta que se encuentra inactiva";
            }
            generateConsignment(productEntity,newEntity);
            newEntity.setTransactionType("debito");

        }else if(newEntity.getMovementType().equals("R")){
            if(amountNotValidToWithdraw(productEntity,newEntity)){
                return "El monto que desea retirar no es valido";
            }
            generateWithdrawal(productEntity,newEntity);
            newEntity.setTransactionType("credito");
        }else {
            return "Tipo de movimiento no valido";
        }


        /*
        switch (newEntity.getMovementType()){
            case "C":{
                generateConsignment(productEntity,newEntity);
                newEntity.setTransactionType("debito");
            }
            case "R":{
                if(amountNotValidToWithdraw(productEntity,newEntity)){
                    return "El monto que desea retirar no es valido";
                }
                generateWithdrawal(productEntity,newEntity);
                newEntity.setTransactionType("credito");
            } default :{

            }
        }*/

        newEntity.setTransactionDate(new Date());
        newEntity.setProduct(productEntity);
        repository.save(newEntity);
        return "Transacción realizada";
    }

    @Override
    public String transfer(String destinationAccount, String rootAccount, Transaction dto) {
        final TransactionEntity destinationTransaction = modelMapper.map(Objects.requireNonNull(dto, "Ocurrio un error al procesar el Body de la peticion"), TransactionEntity.class);
        ProductEntity destinationProduct = productRepository.findByAccountNumber(destinationAccount);
        ProductEntity rootProduct = productRepository.findByAccountNumber(rootAccount);

        if (Objects.isNull(destinationProduct) || Objects.isNull(rootProduct) ){
            return "La cuenta de origen o destino no existe";
        }

        if(amountNotValidToWithdraw(rootProduct,destinationTransaction)){
            return "No tiene Saldo disponible para realizar la transacción";
        }
        generateWithdrawal(rootProduct,destinationTransaction);
        generateConsignment(destinationProduct,destinationTransaction);
        destinationTransaction.setTransactionType("debito");
        destinationTransaction.setTransactionDate(new Date());
        destinationTransaction.setProduct(destinationProduct);
        repository.save(destinationTransaction);
        destinationTransaction.setTransactionType("credito");
        destinationTransaction.setProduct(rootProduct);
        repository.save(destinationTransaction);
        return "Transacción realizada";
    }

    private void generateConsignment(ProductEntity productEntity, TransactionEntity newEntity) {
        double balance = productEntity.getBalance() + newEntity.getValueTransaction();
        double availableBalance = productEntity.getAvailableBalance() + newEntity.getValueTransaction();
        productEntity.setBalance(balance);
        productEntity.setAvailableBalance(availableBalance);
        productRepository.save(productEntity);
    }
    private void generateWithdrawal(ProductEntity productEntity, TransactionEntity newEntity) {
        double balance = productEntity.getBalance() - newEntity.getValueTransaction();
        double availableBalance = productEntity.getAvailableBalance() - newEntity.getValueTransaction();
        productEntity.setBalance(balance);
        productEntity.setAvailableBalance(availableBalance);
        productRepository.save(productEntity);
    }



    private boolean amountNotValidToWithdraw(ProductEntity productEntity, TransactionEntity newEntity) {
        if(productEntity.getAccountType().equals("A")){
            return productEntity.getBalance() - newEntity.getValueTransaction() < 0;
        } else if (productEntity.getAccountType().equals("C")) {
            if(productEntity.getStatus().equals("activa")){
                return productEntity.getBalance() - newEntity.getValueTransaction() < -3000000;
            }else {
                return productEntity.getBalance() - newEntity.getValueTransaction() < 0;
            }
        }
        return true;
    }
}
