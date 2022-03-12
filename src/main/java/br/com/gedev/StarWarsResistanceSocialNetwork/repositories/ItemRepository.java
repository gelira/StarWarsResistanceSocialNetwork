package br.com.gedev.StarWarsResistanceSocialNetwork.repositories;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("select i from Item i where i._id = :item_id")
    Optional<Item> findByUUID(@Param("item_id") UUID itemId);
}
