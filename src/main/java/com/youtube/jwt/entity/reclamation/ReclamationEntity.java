package com.youtube.jwt.entity.reclamation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youtube.jwt.entity.User;

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

    @Enumerated(EnumType.STRING)
    private StateReclamation state;

    @ManyToOne(fetch = FetchType.LAZY) // Many Reclamations can belong to one User
    @JoinColumn(name = "user_name", referencedColumnName = "userName") // Define the foreign key column
    private User user; // Define the User entity relationship

    @Transient // Exclude from database mapping
    private MultipartFile file; // Field for file upload

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reclamationEntity")
    private List<ActionEntity> actionEntities;
}
