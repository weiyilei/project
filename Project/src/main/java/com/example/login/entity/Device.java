package com.example.login.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device")
@Data
public class Device {
    @Id
    @Column(name="id", nullable = false)
    private String id;
    private String category;
    private String brand;
    private String product;
    private String department;
    private String work_status;
    private String buy_date;
    private Integer price;
    private String img_url;
    private String repair_date;
}
