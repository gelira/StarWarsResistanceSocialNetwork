package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Denunciation;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.DenunciationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DenunciationService {
    private final DenunciationRepository denunciationRepository;
    private final RebelService rebelService;

    @Transactional
    public Denunciation createDenunciation(Denunciation denunciation) {
        Denunciation denunciationCreated = denunciationRepository.save(denunciation);
        rebelService.incrementRebelAccusedCount(denunciationCreated.getAccusedRebel());

        return denunciationCreated;
    }
}
