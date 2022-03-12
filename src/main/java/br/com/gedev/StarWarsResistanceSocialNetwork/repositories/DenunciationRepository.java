package br.com.gedev.StarWarsResistanceSocialNetwork.repositories;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Denunciation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DenunciationRepository extends CrudRepository<Denunciation, Long> {
    @Query("select d from Denunciation d " +
            "where d.accuserRebel.id = :accuser_rebel_id and d.accusedRebel.id = :accused_rebel_id")
    Optional<Denunciation> findByAccuserAndAccused(
            @Param("accuser_rebel_id") Long accuserRebelId,
            @Param("accused_rebel_id") Long accusedRebelId);
}
