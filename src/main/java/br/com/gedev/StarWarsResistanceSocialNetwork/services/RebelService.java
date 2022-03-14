package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RebelService {
    private final RebelRepository rebelRepository;
    private final LocationService locationService;

    @Transactional
    public Rebel createRebel(Rebel rebel, Location location) {
        Rebel rebelCreated = rebelRepository.save(rebel);

        location.setRebel(rebelCreated);
        locationService.createLocation(location);

        return rebelCreated;
    }
}
