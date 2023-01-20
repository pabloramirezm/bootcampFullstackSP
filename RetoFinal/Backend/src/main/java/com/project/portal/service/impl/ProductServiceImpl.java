package com.project.portal.service.impl;

import com.project.portal.entity.CustomerEntity;
import com.project.portal.entity.ProductEntity;
import com.project.portal.model.Product;
import com.project.portal.repository.CustomerRepository;
import com.project.portal.repository.ProductRepository;
import com.project.portal.service.ProductServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductServiceInterface {


    private final ProductRepository repository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public ProductServiceImpl(final ProductRepository repository, CustomerRepository customerRepository, final ModelMapper modelMapper){
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.repository = repository;

    }

    @Override
    public List<ProductEntity> findByCustomer(String customerId) {
        CustomerEntity customerEntity = customerRepository.findByidNumber(customerId);
        return repository.findByCustomer(customerEntity);
    }



    @Override
    public ProductEntity create(Product dto, String customerId) {
        final ProductEntity newEntity = modelMapper.map(Objects.requireNonNull(dto, "Ocurrio un error al procesar el Body de la peticion"), ProductEntity.class);
        if(dto.getAccountType().equals("C")){
            double availableBalance = dto.getAvailableBalance() + 3000000;
            dto.setAvailableBalance(availableBalance);
        }
        newEntity.setCreationDate(new Date());
        newEntity.setModificationDate(new Date());
        newEntity.setStatus("activa");
        generateProductId(dto,newEntity);
        CustomerEntity customerEntity = customerRepository.findByidNumber(customerId);
        newEntity.setCustomer(customerEntity);
        return repository.save(newEntity);
    }

    @Override
    public String inactiveProduct(String productId) {
        ProductEntity productEntity = repository.findByAccountNumber(productId);
        if(Objects.isNull(productEntity)){
            return "El producto que intenta inactivar no existe";
        }
        productEntity.setStatus("inactiva");
        productEntity.setModificationDate(new Date());
        repository.save(productEntity);
        return "Producto inactivado";
    }


    @Override
    public String activeProduct(String productId) {
        ProductEntity productEntity = repository.findByAccountNumber(productId);
        if(Objects.isNull(productEntity)){
            return "El producto que intenta activar no existe";
        }
        productEntity.setStatus("activa");
        productEntity.setModificationDate(new Date());
        repository.save(productEntity);
        return "Producto Activado";

    }

    private void generateProductId(Product dto, ProductEntity newEntity) {
        String productId = "";
        if(dto.getAccountType().equals("A")){
            productId = "46".concat(generateRadom(8));
        }else {
            productId = "23".concat(generateRadom(8));
        }

        if(accountNumberExist(productId)){
            generateProductId(dto,newEntity);
        }else {
            newEntity.setAccountNumber(productId);
        }
    }


    @Override
    public String delete(String id) {
        ProductEntity productEntity = repository.findByAccountNumber(id);
        repository.delete(productEntity);
        if(Objects.isNull(productEntity)){
            return "El producto que intenta eliminar no existe";
        }

        if(productEntity.getBalance() == 0.0){
            repository.delete(productEntity);
            return "Producto Eliminado";
        }else {
            return "El prodcuto No se puede Eliminar, debido a que tiene deudas pendientes o el saldo es superior a 0";
        }
    }

    private String generateRadom(int numberCharacters) {
        String numericString ="0123456789";
        StringBuilder productNumber = new StringBuilder(numberCharacters);
        for (int i = 0; i < numberCharacters; i++) {

            int index
                    = (int) (numericString.length()
                    * Math.random());

            productNumber.append(numericString
                    .charAt(index));
        }
        return productNumber.toString();
    }
    private boolean accountNumberExist(String productId) {
        return  Objects.nonNull(repository.findByAccountNumber(productId));
    }
}
