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
import org.springframework.http.ResponseEntity;

@Service
public class ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Autowired
    private StateEstablishmentRepository stateEstablishmentRepository; // Assuming you have a repository for

    @Autowired
    private ActionRepository actionRepository;
    // StateEstablishment

    public List<ReclamationEntity> createReclamation(ReclamationEntity reclamation) {

        // Add additional validation or logic if needed before saving
        reclamationRepository.save(reclamation);
        return reclamationRepository.findAll();

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

    public List<ReclamationEntity> getAll() {
        return reclamationRepository.findAll();
    }

    public ReclamationEntity updateReclamation(Long reclamationId, ReclamationEntity newReclamation) {
        // Retrieve the existing reclamation entity from the database
        ReclamationEntity existingReclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new IllegalArgumentException("Reclamation not found for id: " + reclamationId));

        // Update the existing entity with the new data
        existingReclamation.setName(newReclamation.getName());
        existingReclamation.setDescription(newReclamation.getDescription());
        existingReclamation.setDateCreation(newReclamation.getDateCreation());
        existingReclamation.setDateUpdate(newReclamation.getDateUpdate());
        existingReclamation.setDateClosure(newReclamation.getDateClosure());
        existingReclamation.setState(newReclamation.getState());

        // Save the updated entity to the database
        return reclamationRepository.save(existingReclamation);
    }
}
