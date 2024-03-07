package com.youtube.jwt.service.reclamation;

import org.springframework.stereotype.Service;

import com.youtube.jwt.entity.reclamation.ActionEntity;
import com.youtube.jwt.util.reclamation.ActionRepository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class ActionService {

    private final ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Transactional
    public ActionEntity createAction(ActionEntity action) {
        // Optionally, you can perform additional business logic here before saving
        return actionRepository.save(action);
    }

    @Transactional
    public ActionEntity updateAction(ActionEntity action) {
        // Optionally, you can perform additional business logic here before saving
        return actionRepository.save(action);
    }

    @Transactional
    public void deleteAction(Long id) {
        // Optionally, you can perform additional business logic here before deletion
        actionRepository.deleteById(id);
    }

    public ActionEntity getActionById(Long id) {
        // Use the repository's findById method to get the ActionEntity by ID
        Optional<ActionEntity> optionalAction = actionRepository.findById(id);

        // Check if the ActionEntity exists and return it, or return null if not found
        return optionalAction.orElse(null);
    }
    public List<ActionEntity> getAllActions() {
        // Use the repository's findAll method to get all actions
        return actionRepository.findAll();
    }
}

