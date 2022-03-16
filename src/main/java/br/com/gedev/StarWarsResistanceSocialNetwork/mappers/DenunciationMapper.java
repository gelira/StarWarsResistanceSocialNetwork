package br.com.gedev.StarWarsResistanceSocialNetwork.mappers;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.DenunciationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Denunciation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DenunciationMapper {
    private final ModelMapper modelMapper;
    private final RebelMapper rebelMapper;

    public DenunciationDTO fromEntityToDTO(Denunciation entity) {
        DenunciationDTO dto = modelMapper.map(entity, DenunciationDTO.class);
        dto.setAccusedRebel(rebelMapper.fromEntityToRebelNameDTO(entity.getAccusedRebel()));
        dto.setAccuserRebel(rebelMapper.fromEntityToRebelNameDTO(entity.getAccuserRebel()));

        return dto;
    }
}
