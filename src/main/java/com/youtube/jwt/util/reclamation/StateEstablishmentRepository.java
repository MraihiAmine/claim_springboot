package com.youtube.jwt.util.reclamation;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtube.jwt.entity.reclamation.StateEstablishment;

public interface StateEstablishmentRepository extends JpaRepository<StateEstablishment, Long> {
    // You can add custom query methods if needed
}
