package br.com.gedev.StarWarsResistanceSocialNetwork.repositories;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocationRepository extends CrudRepository<Location, Long> {
    @Modifying
    @Query("update Location l " +
            "set l.current = false, l.updatedAt = CURRENT_TIMESTAMP " +
            "where l.rebel.id = :rebel_id and l.current = true")
    void updateLocationsCurrentToFalse(@Param("rebel_id") Long rebelId);

    @Query("select l from Location l where l.rebel.id = :rebel_id and l.current = true")
    Optional<Location> findCurrentLocation(@Param("rebel_id") Long rebelId);
}
