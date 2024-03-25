package com.youtube.jwt.entity.reclamation;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "state_establishments")
@Data
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
    // Constructors, getters, setters
}
