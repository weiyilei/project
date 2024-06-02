package com.example.login.entity;


import lombok.Data;

import javax.persistence.*;

//modified

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    private String telephone;
    private String password;
    private String nickname;
    private String img;
    private String type;
}
