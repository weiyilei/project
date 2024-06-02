package com.example.login.repository;

import com.example.login.entity.RepairRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRecordRepository extends JpaRepository<RepairRecord, Integer> {

    int countAllByCause(String cause);

    RepairRecord findById(String id);

    List<RepairRecord> findAllByRepairstatus(String repairStatus);
}
