package br.com.gedev.StarWarsResistanceSocialNetwork.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "rebels")
public class Rebel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID _id;
    private String name;
    private Integer age;
    private Integer accusedCount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany
    @JoinColumn(name = "rebel_id")
    private List<Location> locations;

    @OneToMany
    @JoinColumn(name = "rebel_id")
    private List<ItemRebel> itemsRebel;

    @PrePersist
    private void prePersist() {
        _id = UUID.randomUUID();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = new Date();
    }
}
