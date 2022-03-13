package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RebelService {
    private final RebelRepository rebelRepository;

    public Rebel createRebel(Rebel rebel) {
        return rebelRepository.save(rebel);
    }
}
