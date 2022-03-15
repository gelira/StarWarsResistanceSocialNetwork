package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Optional<Location> findCurrentLocation(Rebel rebel) {
        return locationRepository.findCurrentLocation(rebel.getId());
    }

    @Transactional
    public Location createLocation(Location location, Rebel rebel) {
        locationRepository.updateLocationsCurrentToFalse(rebel.getId());
        location.setRebel(rebel);
        return createLocation(location);
    }
}
