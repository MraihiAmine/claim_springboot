package com.youtube.jwt.entity.reclamation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActionUpdateRequest {

    private String actionName;
    private String description;

    // Constructors, getters, setters, etc.
}
