package br.com.gedev.StarWarsResistanceSocialNetwork.repositories;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RebelRepository extends CrudRepository<Rebel, Long> {
    @Query("select r from Rebel r where r._id = :rebel_id")
    Optional<Rebel> findByUUID(@Param("rebel_id") UUID rebelId);

    @Modifying
    @Query("update Rebel r " +
            "set r.accusedCount = r.accusedCount + 1 " +
            "where r.id = :rebel_id")
    void incrementRebelAccusedCount(@Param("rebel_id") Long rebelId);

    @Query("select count(r) from Rebel r")
    long countAllRebels();

    @Query("select count(r) from Rebel r where r.accusedCount < :accused_count_limit")
    long countRebels(@Param("accused_count_limit") int accusedCountLimit);

    @Query("select count(r) from Rebel r where r.accusedCount >= :accused_count_limit")
    long countTraitors(@Param("accused_count_limit") int accusedCountLimit);

    @Query("select r from Rebel r order by r.name asc, r.createdAt desc")
    List<Rebel> findAllRebels();
}
