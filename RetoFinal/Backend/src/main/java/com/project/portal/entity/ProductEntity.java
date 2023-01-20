package com.project.portal.entity;

import com.project.portal.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "status")
    private String status;
    @Column(name = "balance")
    private double balance;
    @Column(name = "available_balance")
    private double availableBalance;
    @Column(name = "exempt_gmf")
    private boolean exemptGMF;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "modification_date")
    private Date modificationDate;

    @JoinColumn(name = "id_customer", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private CustomerEntity customer;
}
