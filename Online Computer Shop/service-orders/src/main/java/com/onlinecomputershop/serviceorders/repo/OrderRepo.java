package com.onlinecomputershop.serviceorders.repo;

import com.onlinecomputershop.serviceorders.repo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
}
