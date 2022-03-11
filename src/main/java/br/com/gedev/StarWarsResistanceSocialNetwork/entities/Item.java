package br.com.gedev.StarWarsResistanceSocialNetwork.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private UUID _id;

    private String name;
    private Double value;

    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
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
