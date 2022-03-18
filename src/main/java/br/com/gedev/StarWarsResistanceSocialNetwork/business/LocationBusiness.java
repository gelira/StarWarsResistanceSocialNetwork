package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateLocationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.LocationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.RebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.LocationMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.LocationService;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class LocationBusiness {
    private final RebelService rebelService;
    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @Transactional
    public LocationDTO createNewLocation(UUID rebelId, CreateLocationDTO createLocationDTO)
            throws RebelNotFoundException {

        Rebel rebel = rebelService.findRebelByUUID(rebelId);
        Location locationToCreate = locationMapper.fromCreateDTOToEntity(createLocationDTO);
        Location locationCreated = locationService.createLocation(locationToCreate, rebel);

        return locationMapper.fromEntityToDTO(locationCreated);
    }
}
