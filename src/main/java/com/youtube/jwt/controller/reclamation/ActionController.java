package com.youtube.jwt.controller.reclamation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.jwt.entity.reclamation.ActionEntity;
import com.youtube.jwt.entity.reclamation.ActionUpdateRequest;
import com.youtube.jwt.service.reclamation.ActionService;

@RestController
@RequestMapping("/actions")
public class ActionController {

    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("/")
    public List<ActionEntity> getAllActions() {
        return actionService.getAllActions();
    }

    @PostMapping("addAction")
    public ResponseEntity<ActionEntity> createAction(@RequestBody ActionEntity action) {
        System.out.println("entered in the save action");
        ActionEntity savedAction = actionService.createAction(action);
        return ResponseEntity.ok(savedAction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActionEntity> getActionById(@PathVariable Long id) {
        ActionEntity action = actionService.getActionById(id);
        if (action != null) {
            return ResponseEntity.ok(action);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("updateAction/{id}")
    public ResponseEntity<ActionEntity> updateAction(
            @PathVariable Long id,
            @RequestBody ActionUpdateRequest actionUpdateRequest) {
        ActionEntity existingAction = actionService.getActionById(id);
        if (existingAction != null) {
            // Update only the title and description fields
            existingAction.setActionName(actionUpdateRequest.getActionName());
            existingAction.setDescription(actionUpdateRequest.getDescription());

            ActionEntity updatedAction = actionService.updateAction(existingAction);
            return ResponseEntity.ok(updatedAction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("deleteAction/{id}")
    public ResponseEntity<Void> deleteAction(@PathVariable Long id) {
        ActionEntity existingAction = actionService.getActionById(id);
        if (existingAction != null) {
            actionService.deleteAction(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
