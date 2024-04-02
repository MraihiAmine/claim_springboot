package com.youtube.jwt.controller.reclamation;

import org.json.JSONObject;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.youtube.jwt.entity.reclamation.ActionEntity;
import com.youtube.jwt.entity.reclamation.ReclamationEntity;
import com.youtube.jwt.service.reclamation.ReclamationService;
import com.youtube.jwt.util.exception.NotFoundException;

@RestController
@RequestMapping("/reclamations")
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;

    @PostMapping("addClaim")
    public ResponseEntity<List<ReclamationEntity>> createReclamation(
            @RequestBody ReclamationEntity reclamation) {
        return ResponseEntity.ok(reclamationService.createReclamation(reclamation));
    }

    @PutMapping("updateClaim/{reclamationId}")
    public ResponseEntity<ReclamationEntity> updateReclamation(
            @RequestBody ReclamationEntity reclamation, @PathVariable Long reclamationId) {
        return ResponseEntity.ok(reclamationService.updateReclamation(reclamationId, reclamation));
    }

    @GetMapping("/")
    public ResponseEntity<List<ReclamationEntity>> getClaims() {
        return ResponseEntity.ok(reclamationService.getAll());
    }

    @PostMapping("/{reclamationId}/addAction")
    public ResponseEntity<ReclamationEntity> addActionToReclamation(
            @PathVariable Long reclamationId,
            @RequestBody ActionEntity action) {
        return ResponseEntity.ok(reclamationService.addActionToReclamation(reclamationId, action));
    }

    @PostMapping("/{reclamationId}/removeAction/{actionId}")
    public ResponseEntity<ReclamationEntity> removeActionFromReclamation(
            @PathVariable Long reclamationId,
            @PathVariable Long actionId) {
        return ResponseEntity.ok(reclamationService.removeActionFromReclamation(reclamationId, actionId));
    }

    @DeleteMapping("deleteClaim/{reclamationId}")
    public ResponseEntity<String> deleteReclamation(@PathVariable Long reclamationId) {
        // JSONObject errorJson = new JSONObject();
        try {
            reclamationService.deleteReclamation(reclamationId);
            // errorJson.put("message", "Reclamation with ID " + reclamationId + " has been
            // deleted.");
            return ResponseEntity.ok().body("Reclamation with ID " + reclamationId + " has been deleted.");
        } catch (NotFoundException e) {
            // errorJson.put("error", "Reclamation with ID " + reclamationId + " not
            // found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reclamation with ID " + reclamationId + " not found");
        }
    }
}
