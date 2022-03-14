package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.LocationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.RebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.RebelMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RebelService {
    private final RebelRepository rebelRepository;
    private final LocationService locationService;
    private final RebelMapper rebelMapper;

    @Transactional
    public RebelDTO createRebel(CreateRebelDTO createRebelDTO) {
        Rebel toCreate = rebelMapper.fromCreateDTOToEntity(createRebelDTO);
        Rebel created = rebelRepository.save(toCreate);

        LocationDTO locationDTO = locationService.createLocation(createRebelDTO.getLocation(), created);

        RebelDTO dto = rebelMapper.fromEntityToRebelDTO(created);
        dto.setLocation(locationDTO);

        return dto;
    }
}
