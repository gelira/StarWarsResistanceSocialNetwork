package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateDenunciationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Denunciation;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.AccusedRebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.AccuserRebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.AutoDenunciationException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.RebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DenunciationBusiness {
    private final RebelService rebelService;

    public Denunciation validateDenunciation(CreateDenunciationDTO createDenunciationDTO)
            throws AutoDenunciationException, AccuserRebelNotFoundException, AccusedRebelNotFoundException {
        UUID accusedId = createDenunciationDTO.getAccusedRebelId();
        UUID accuserId = createDenunciationDTO.getAccuserRebelId();

        if (Objects.equals(accuserId, accusedId)) {
            throw new AutoDenunciationException();
        }

        Denunciation denunciation = new Denunciation();

        try {
            denunciation.setAccuserRebel(rebelService.findRebelByUUID(accuserId));
        } catch (RebelNotFoundException e) {
            throw new AccuserRebelNotFoundException();
        }

        try {
            denunciation.setAccusedRebel(rebelService.findRebelByUUID(accusedId));
        } catch (RebelNotFoundException e) {
            throw new AccusedRebelNotFoundException();
        }

        return denunciation;
    }
}
