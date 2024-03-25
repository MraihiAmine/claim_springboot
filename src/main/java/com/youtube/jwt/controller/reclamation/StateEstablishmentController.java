package com.youtube.jwt.controller.reclamation;


import org.springframework.web.bind.annotation.*;

import com.youtube.jwt.entity.reclamation.StateEstablishment;
import com.youtube.jwt.service.reclamation.StateEstablishmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/state-establishments")
public class StateEstablishmentController {

    private final StateEstablishmentService stateEstablishmentService;

    public StateEstablishmentController(StateEstablishmentService stateEstablishmentService) {
        this.stateEstablishmentService = stateEstablishmentService;
    }

    @GetMapping
    public List<StateEstablishment> getAllStateEstablishments() {
        return stateEstablishmentService.getAllStateEstablishments();
    }

    @GetMapping("/{id}")
    public Optional<StateEstablishment> getStateEstablishmentById(@PathVariable Long id) {
        return stateEstablishmentService.getStateEstablishmentById(id);
    }

    @PostMapping
    public StateEstablishment createStateEstablishment(@RequestBody StateEstablishment stateEstablishment) {
        return stateEstablishmentService.createStateEstablishment(stateEstablishment);
    }

    @PutMapping("/{id}")
    public StateEstablishment updateStateEstablishment(@PathVariable Long id, @RequestBody StateEstablishment updatedStateEstablishment) {
        return stateEstablishmentService.updateStateEstablishment(id, updatedStateEstablishment);
    }

    @DeleteMapping("/{id}")
    public void deleteStateEstablishment(@PathVariable Long id) {
        stateEstablishmentService.deleteStateEstablishment(id);
    }
}

