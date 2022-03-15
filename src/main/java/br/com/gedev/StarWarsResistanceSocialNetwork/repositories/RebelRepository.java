package br.com.gedev.StarWarsResistanceSocialNetwork.repositories;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RebelRepository extends CrudRepository<Rebel, Long> {
    @Query("select r from Rebel r where r._id = :rebel_id")
    Optional<Rebel> findByUUID(@Param("rebel_id") UUID rebelId);
}
