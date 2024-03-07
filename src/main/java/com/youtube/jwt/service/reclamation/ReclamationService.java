package com.youtube.jwt.service.reclamation;

import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.youtube.jwt.entity.reclamation.ActionEntity;
import com.youtube.jwt.entity.reclamation.ReclamationEntity;
import com.youtube.jwt.entity.reclamation.StateEstablishment;
import com.youtube.jwt.util.reclamation.ActionRepository;
import com.youtube.jwt.util.reclamation.ReclamationRepository;
import com.youtube.jwt.util.reclamation.StateEstablishmentRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private StateEstablishmentRepository stateEstablishmentRepository; // Assuming you have a repository for

    @Autowired
    private ActionRepository actionRepository;
    // StateEstablishment

    public ReclamationEntity createReclamation(Long stateEstablishmentId, ReclamationEntity reclamation) {
        StateEstablishment stateEstablishment = stateEstablishmentRepository.findById(stateEstablishmentId)
                .orElseThrow(() -> new EntityNotFoundException("State Establishment not found"));

        reclamation.setStateEstablishment(stateEstablishment);

        // Add additional validation or logic if needed before saving
        return reclamationRepository.save(reclamation);
    }

    public ReclamationEntity addActionToReclamation(Long reclamationId, ActionEntity action) {
        ReclamationEntity reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found"));

        action.setReclamationEntity(reclamation);
        actionRepository.save(action);

        reclamation.getActionEntities().add(action);
        return reclamationRepository.save(reclamation);
    }

    public ReclamationEntity removeActionFromReclamation(Long reclamationId, Long actionId) {
        ReclamationEntity reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new EntityNotFoundException("Reclamation not found"));

        // Add additional logic if needed
        List<ActionEntity> actions = reclamation.getActionEntities();
        actions.removeIf(action -> action.getId().equals(actionId));
        return reclamationRepository.save(reclamation);
    }
}
