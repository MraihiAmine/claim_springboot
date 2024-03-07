package com.youtube.jwt.entity.reclamation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "reclamation")
public class ReclamationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Creation date is required")
    private Date dateCreation;

    private Date dateUpdate;
    private Date dateClosure;

    @NotNull(message = "State is required")
    @Enumerated(EnumType.STRING)
    private StateReclamation state;

    @Column(nullable = false)
    private int year;

    public void setYear(int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (year >= 2023 && year <= currentYear) {
            this.year = year;
        } else {
            throw new IllegalArgumentException("Year should be between 2023 and the current year.");
        }
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "state_establishment_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StateEstablishment stateEstablishment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reclamationEntity")
    private List<ActionEntity> actionEntities;
}
