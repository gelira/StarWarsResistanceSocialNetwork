package br.com.gedev.StarWarsResistanceSocialNetwork.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID _id;
    private String name;
    private Double value;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    private void setUUID() {
        _id = UUID.randomUUID();
    }

    @PreUpdate
    private void setUpdatedTimestamp() {
        updatedAt = new Date();
    }
}
