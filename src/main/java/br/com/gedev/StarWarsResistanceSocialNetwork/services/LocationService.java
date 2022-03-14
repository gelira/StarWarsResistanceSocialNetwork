package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateLocationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.LocationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.LocationMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationDTO createLocation(CreateLocationDTO createLocationDTO) {
        Location toCreate = locationMapper.fromCreateDTOToEntity(createLocationDTO);
        Location created = locationRepository.save(toCreate);
        return locationMapper.fromEntityToDTO(created);
    }
}
