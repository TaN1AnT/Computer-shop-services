package com.onlinecomputershop.servicebrands.repo;

import com.onlinecomputershop.servicebrands.repo.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
}
