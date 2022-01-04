package com.onlinecomputershop.servicetypes.repo;

import com.onlinecomputershop.servicetypes.repo.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepo extends JpaRepository<Type, Long> {
}
