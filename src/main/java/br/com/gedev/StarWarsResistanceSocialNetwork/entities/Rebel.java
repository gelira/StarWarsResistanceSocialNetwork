package br.com.gedev.StarWarsResistanceSocialNetwork.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "rebels")
public class Rebel {
    public static final int LIMIT_TRAITOR_DENUNCIATIONS = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private UUID _id;

    private String name;
    private Integer age;
    private String genre;
    private Integer accusedCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date updatedAt;

    @OneToMany(mappedBy = "rebel")
    @Setter(AccessLevel.NONE)
    private List<Location> locations;

    @OneToMany(mappedBy = "rebel")
    @Setter(AccessLevel.NONE)
    private List<ItemRebel> itemsRebel;

    @PrePersist
    private void prePersist() {
        _id = UUID.randomUUID();
        accusedCount = 0;
        createdAt = new Date();
        updatedAt = new Date();
        locations = new ArrayList<>();
        itemsRebel = new ArrayList<>();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = new Date();
    }

    public boolean isTraitor() {
        return accusedCount >= LIMIT_TRAITOR_DENUNCIATIONS;
    }
}
