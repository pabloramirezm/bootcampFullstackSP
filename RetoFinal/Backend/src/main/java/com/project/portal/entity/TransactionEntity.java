package com.project.portal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.portal.model.Product;
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
@Table(name = "transaction")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "description")
    private String description;
    @Column(name = "value_transaction")
    private double valueTransaction;
    @Column(name = "movement_type")
    private String movementType;
    @Column(name = "transaction_date")
    private Date transactionDate;

    @JoinColumn(name = "id_product", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private ProductEntity product;
}
