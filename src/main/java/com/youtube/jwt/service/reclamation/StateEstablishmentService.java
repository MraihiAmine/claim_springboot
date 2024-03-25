package com.youtube.jwt.service.reclamation;

import org.springframework.stereotype.Service;

import com.youtube.jwt.entity.reclamation.StateEstablishment;
import com.youtube.jwt.util.reclamation.StateEstablishmentRepository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@Service
public class StateEstablishmentService {

    private final StateEstablishmentRepository stateEstablishmentRepository;

    public StateEstablishmentService(StateEstablishmentRepository stateEstablishmentRepository) {
        this.stateEstablishmentRepository = stateEstablishmentRepository;
    }

    public List<StateEstablishment> getAllStateEstablishments() {
        return stateEstablishmentRepository.findAll();
    }

    public Optional<StateEstablishment> getStateEstablishmentById(Long id) {
        return stateEstablishmentRepository.findById(id);
    }

    public StateEstablishment createStateEstablishment(StateEstablishment stateEstablishment) {
        // Handle relationships in the ReclamationService createReclamation method
        System.out.println("stateEstablishment  ");
        System.out.println(stateEstablishment);
        return stateEstablishmentRepository.save(stateEstablishment);
    }

    public StateEstablishment updateStateEstablishment(Long id, StateEstablishment updatedStateEstablishment) {
    StateEstablishment existingStateEstablishment = stateEstablishmentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("State Establishment not found with ID: " + id));

    existingStateEstablishment.setName(updatedStateEstablishment.getName());
    existingStateEstablishment.setFax(updatedStateEstablishment.getFax());
    existingStateEstablishment.setCity(updatedStateEstablishment.getCity());

    return stateEstablishmentRepository.save(existingStateEstablishment);
}


    public void deleteStateEstablishment(Long id) {
        stateEstablishmentRepository.deleteById(id);
    }
}
