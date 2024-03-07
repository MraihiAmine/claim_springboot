package com.youtube.jwt.util.reclamation;


import org.springframework.data.jpa.repository.JpaRepository;

import com.youtube.jwt.entity.reclamation.ActionEntity;

public interface ActionRepository extends JpaRepository<ActionEntity, Long> {
    // You can add custom query methods here if needed
}

