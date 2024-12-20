package com.hutech.demo.repository;

import com.hutech.demo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsById(Long id);
    Optional<Booking> findById(Long id);
}

