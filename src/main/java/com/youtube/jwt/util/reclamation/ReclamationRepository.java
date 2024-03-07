package com.youtube.jwt.util.reclamation;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtube.jwt.entity.reclamation.ReclamationEntity;

public interface ReclamationRepository extends JpaRepository<ReclamationEntity, Long> {

    ReclamationEntity getReclamationById(Long id);
    // You can add custom query methods here if needed
}
