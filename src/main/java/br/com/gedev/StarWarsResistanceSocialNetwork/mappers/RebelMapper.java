package br.com.gedev.StarWarsResistanceSocialNetwork.mappers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.RebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RebelMapper {
    private final ModelMapper modelMapper;

    public Rebel fromCreateDTOToEntity(CreateRebelDTO dto) {
        return modelMapper.map(dto, Rebel.class);
    }

    public RebelDTO fromEntityToRebelDTO(Rebel entity) {
        return modelMapper.map(entity, RebelDTO.class);
    }
}
