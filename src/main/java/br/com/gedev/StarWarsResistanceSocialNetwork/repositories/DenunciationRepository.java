package br.com.gedev.StarWarsResistanceSocialNetwork.repositories;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Denunciation;
import org.springframework.data.repository.CrudRepository;

public interface DenunciationRepository extends CrudRepository<Denunciation, Long> {
}
