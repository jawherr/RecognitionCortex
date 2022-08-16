package com.neocortex.recognitioncortex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "title", length = 500, unique = true)
    private String title;


    @Type(type = "text")
    @Lob
    @Column(name = "content", length = 8000)
    @NotEmpty
    private String content;

    @Column(name = "createdOn")
    private Instant createdOn;

    @Column(name = "updatedOn")
    private Instant updatedOn;

    @Column(name = "username", length = 500)
    @NotEmpty
    private String username;


    @NotNull
    @JoinColumn(name = "utilisateur_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Utilisateur utilisateur;
}
