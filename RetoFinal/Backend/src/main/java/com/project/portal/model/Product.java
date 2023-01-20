package com.project.portal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private Long id;
    private String accountNumber;
    private String accountType;
    private String status;
    private double balance;
    private double availableBalance;
    private boolean exemptGMF;
    private Date creationDate;
    private Date modificationDate;
    private Customer customer;

}
