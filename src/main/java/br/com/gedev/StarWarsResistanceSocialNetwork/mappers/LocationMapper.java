package br.com.gedev.StarWarsResistanceSocialNetwork.mappers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateLocationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.LocationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LocationMapper {
    private final ModelMapper modelMapper;

    public Location fromCreateDTOToEntity(CreateLocationDTO dto) {
        return modelMapper.map(dto, Location.class);
    }

    public LocationDTO fromEntityToDTO(Location entity) {
        return modelMapper.map(entity, LocationDTO.class);
    }
}
