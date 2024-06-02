package com.example.login.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unionIdTable")
@Data
public class UnionIdRelationship {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String userId;

    private String openId;

    private String unionId;

    public UnionIdRelationship(String id, String userId, String openId, String unionId) {
        this.id = id;
        this.userId = userId;
        this.openId = openId;
        this.unionId = unionId;
    }

    public UnionIdRelationship() {

    }
}
