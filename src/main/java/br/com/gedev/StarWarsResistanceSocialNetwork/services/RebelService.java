package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.RebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RebelService {
    private final RebelRepository rebelRepository;
    private final LocationService locationService;
    private final ItemRebelService itemRebelService;

    @Transactional
    public Rebel createRebel(Rebel rebel, Location location, List<ItemRebel> itemRebelList) {
        Rebel rebelCreated = rebelRepository.save(rebel);

        location.setRebel(rebelCreated);
        locationService.createLocation(location);

        for (ItemRebel itemRebel : itemRebelList) {
            itemRebel.setRebel(rebelCreated);
            itemRebelService.createItemRebel(itemRebel);
        }

        return rebelCreated;
    }

    public Rebel findRebelByUUID(UUID rebelId) throws RebelNotFoundException {
        return rebelRepository.findByUUID(rebelId)
                .orElseThrow(RebelNotFoundException::new);
    }
}
