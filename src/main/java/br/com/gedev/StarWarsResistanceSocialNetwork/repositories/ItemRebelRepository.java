package br.com.gedev.StarWarsResistanceSocialNetwork.repositories;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRebelRepository extends CrudRepository<ItemRebel, Long> {
    @Query("select i from ItemRebel i where i.rebel.id = :rebel_id and i.item.id = :item_id")
    Optional<ItemRebel> findByRebelAndItem(@Param("rebel_id") Long rebelId, @Param("item_id") Long itemId);

    @Modifying
    @Query("update ItemRebel i " +
            "set i.quantity = i.quantity + :change, i.updatedAt = current_timestamp " +
            "where i.rebel.id = :rebel_id and i.item.id = :item_id")
    void updateQuantityItemRebel(
            @Param("rebel_id") Long rebelId, @Param("item_id") Long itemId, @Param("change") int change);
}
