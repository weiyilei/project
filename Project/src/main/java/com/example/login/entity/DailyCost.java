package com.example.login.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cost")
@Data
public class DailyCost {

    @Id
    @Column(name="id", nullable = false)
    private String id;
    private String date;
    private Integer cost;
}
