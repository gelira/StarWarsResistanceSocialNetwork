package br.com.gedev.StarWarsResistanceSocialNetwork.repositories;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Deal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DealRepository extends CrudRepository<Deal, Long> {
    @Query("select d from Deal d order by d.createdAt desc")
    List<Deal> findAllDeals();
}
