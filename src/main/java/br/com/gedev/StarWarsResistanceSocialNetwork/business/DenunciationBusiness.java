package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateDenunciationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.DenunciationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Denunciation;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.*;
import br.com.gedev.StarWarsResistanceSocialNetwork.mappers.DenunciationMapper;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.DenunciationService;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DenunciationBusiness {
    private final RebelService rebelService;
    private final DenunciationService denunciationService;
    private final DenunciationMapper denunciationMapper;

    @Transactional
    public DenunciationDTO createDenunciation(CreateDenunciationDTO createDenunciationDTO)
            throws AccusedRebelNotFoundException, AutoDenunciationException,
            AccuserRebelNotFoundException, RepeatedDenunciationException {

        Denunciation denunciationToCreate = validateDenunciation(createDenunciationDTO);
        Denunciation denunciationCreated = denunciationService.createDenunciation(denunciationToCreate);
        return denunciationMapper.fromEntityToDTO(denunciationCreated);
    }

    public Denunciation validateDenunciation(CreateDenunciationDTO createDenunciationDTO)
            throws AutoDenunciationException, AccuserRebelNotFoundException,
                AccusedRebelNotFoundException, RepeatedDenunciationException {
        UUID accusedId = createDenunciationDTO.getAccusedRebelId();
        UUID accuserId = createDenunciationDTO.getAccuserRebelId();

        if (Objects.equals(accuserId, accusedId)) {
            throw new AutoDenunciationException();
        }

        Denunciation denunciation = new Denunciation();
        Rebel accuserRebel, accusedRebel;

        try {
            accuserRebel = rebelService.findRebelByUUID(accuserId);
        } catch (RebelNotFoundException e) {
            throw new AccuserRebelNotFoundException();
        }

        try {
            accusedRebel = rebelService.findRebelByUUID(accusedId);
        } catch (RebelNotFoundException e) {
            throw new AccusedRebelNotFoundException();
        }

        // check unique pair accuser rebel and accused rebel
        if (denunciationService.findByAccuserAndAccused(accuserRebel, accusedRebel).isPresent()) {
            throw new RepeatedDenunciationException();
        }

        denunciation.setAccuserRebel(accuserRebel);
        denunciation.setAccusedRebel(accusedRebel);

        return denunciation;
    }
}
