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
@Table(name = "deals")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private UUID _id;

    @ManyToOne
    @JoinColumn(name = "rebel1_id")
    private Rebel rebel1;

    @ManyToOne
    @JoinColumn(name = "rebel2_id")
    private Rebel rebel2;

    @OneToMany(mappedBy = "deal")
    @Setter(AccessLevel.NONE)
    private List<DealItem> dealItems;

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
        dealItems = new ArrayList<>();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = new Date();
    }
}
