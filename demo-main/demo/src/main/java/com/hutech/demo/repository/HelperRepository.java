package com.hutech.demo.repository;

import com.hutech.demo.model.Helper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HelperRepository extends JpaRepository<Helper, Long> {
    Optional<Helper> findById(Long id);
}
