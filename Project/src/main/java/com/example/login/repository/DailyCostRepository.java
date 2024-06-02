package com.example.login.repository;

import com.example.login.entity.DailyCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyCostRepository extends JpaRepository<DailyCost, Integer> {

    DailyCost findByDate(String date);
}
