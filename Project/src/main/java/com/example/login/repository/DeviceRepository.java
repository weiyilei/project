package com.example.login.repository;

import com.example.login.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    Device findById(String id);

    void deleteById(String id);
}
