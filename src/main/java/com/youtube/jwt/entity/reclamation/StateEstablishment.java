package com.youtube.jwt.entity.reclamation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "state_establishments")
@Data
@EqualsAndHashCode(exclude = "reclamationEntities")
public class StateEstablishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "fax")
    private String fax;

    @Column(name = "city")
    private String city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateEstablishment")
    private List<ReclamationEntity> reclamationEntities;
    // Constructors, getters, setters
}
