package br.com.gedev.StarWarsResistanceSocialNetwork.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "deals")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID _id;

    @ManyToOne
    @JoinColumn(name = "rebel1_id")
    private Rebel rebel1;

    @ManyToOne
    @JoinColumn(name = "rebel2_id")
    private Rebel rebel2;

    @OneToMany
    @JoinColumn(name = "deal_id")
    private List<DealItem> dealItems;

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
