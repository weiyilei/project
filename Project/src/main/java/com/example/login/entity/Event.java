package com.example.login.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
@Data
public class Event {

    @Id
    @Column(name="id", nullable = false)
    private String id;
    private String date;
    private String user;
    private String detail;
}
