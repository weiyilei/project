package com.example.login.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "repair_record")
@Data
public class RepairRecord {

    @Id
    @Column(name = "id", nullable = false)
    private String id;
    private String device_id;
    private String cause;
    private String detail;
    private String report_date;
    private String repairstatus;
    private String name;
    private String expenditure;
    private String repair_date;
    private String img_url;

}
