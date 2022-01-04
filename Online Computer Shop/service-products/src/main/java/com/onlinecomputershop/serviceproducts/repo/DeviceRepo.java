package com.onlinecomputershop.serviceproducts.repo;

import com.onlinecomputershop.serviceproducts.repo.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepo extends JpaRepository<Device, Long> {
}
