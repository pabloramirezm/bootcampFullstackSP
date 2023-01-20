package com.project.portal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Customer {
    private Long id;
    private String typeId;
    private String rol;
    private String idNumber;
    private String name;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private Date creationDate;
    private Date modificationDate;


}
