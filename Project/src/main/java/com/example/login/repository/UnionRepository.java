package com.example.login.repository;

import com.example.login.entity.UnionIdRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnionRepository extends JpaRepository<UnionIdRelationship, Integer> {

    UnionIdRelationship findByUnionId(String unionId);
}
