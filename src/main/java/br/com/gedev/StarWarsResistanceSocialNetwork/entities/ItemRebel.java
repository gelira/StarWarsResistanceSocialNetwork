package br.com.gedev.StarWarsResistanceSocialNetwork.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "items_rebels")
public class ItemRebel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private UUID _id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "rebel_id")
    private Rebel rebel;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date updatedAt;

    @PrePersist
    private void prePersist() {
        _id = UUID.randomUUID();
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = new Date();
    }
}
