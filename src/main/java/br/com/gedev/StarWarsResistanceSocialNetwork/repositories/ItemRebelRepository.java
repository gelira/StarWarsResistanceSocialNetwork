package br.com.gedev.StarWarsResistanceSocialNetwork.repositories;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRebelRepository extends CrudRepository<ItemRebel, Long> {
    @Query("select i from ItemRebel i where i.rebel.id = :rebel_id and i.item.id = :item_id")
    Optional<ItemRebel> findByRebelAndItem(@Param("rebel_id") Long rebelId, @Param("item_id") Long itemId);

    @Query("select i from ItemRebel i where i.rebel.id = :rebel_id")
    List<ItemRebel> findByRebelId(@Param("rebel_id") Long rebelI);
}
