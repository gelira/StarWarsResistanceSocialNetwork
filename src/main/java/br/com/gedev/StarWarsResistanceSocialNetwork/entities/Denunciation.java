package br.com.gedev.StarWarsResistanceSocialNetwork.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "denunciations")
public class Denunciation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID _id;

    @ManyToOne
    @JoinColumn(name = "accuser_rebel_id")
    private Rebel accuserRebel;

    @ManyToOne
    @JoinColumn(name = "accused_rebel_id")
    private Rebel accusedRebel;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    private void prePersist() {
        _id = UUID.randomUUID();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = new Date();
    }
}
