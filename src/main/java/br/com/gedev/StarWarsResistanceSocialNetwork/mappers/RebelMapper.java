package br.com.gedev.StarWarsResistanceSocialNetwork.mappers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.RebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RebelMapper {
    private final ModelMapper modelMapper;
    private final LocationMapper locationMapper;
    private final LocationService locationService;

    public Rebel fromCreateDTOToEntity(CreateRebelDTO dto) {
        return modelMapper.map(dto, Rebel.class);
    }

    public RebelDTO fromEntityToRebelDTO(Rebel entity) {
        RebelDTO dto = modelMapper.map(entity, RebelDTO.class);

        Optional<Location> locationOptional = locationService.findCurrentLocation(entity);
        locationOptional.ifPresent(location -> dto.setLocation(locationMapper.fromEntityToDTO(location)));

        return dto;
    }
}
