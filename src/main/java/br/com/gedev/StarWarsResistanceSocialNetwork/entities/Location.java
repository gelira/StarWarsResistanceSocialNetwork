package br.com.gedev.StarWarsResistanceSocialNetwork.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private UUID _id;

    private Double latitude;
    private Double longitude;
    private String galaxyName;
    private Boolean current;

    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "rebel_id")
    private Rebel rebel;

    @PrePersist
    private void prePersist() {
        _id = UUID.randomUUID();
        current = true;
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = new Date();
    }
}
