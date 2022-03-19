package br.com.gedev.StarWarsResistanceSocialNetwork.services;

import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Location;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.RebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RebelService {
    private final RebelRepository rebelRepository;
    private final LocationService locationService;
    private final ItemRebelService itemRebelService;

    public List<Rebel> listAllRebels() {
        return rebelRepository.findAllRebels();
    }

    public Rebel createRebel(Rebel rebel, Location location, List<ItemRebel> itemRebelList) {
        Rebel rebelCreated = rebelRepository.save(rebel);

        location.setRebel(rebelCreated);
        Location locationCreated = locationService.createLocation(location);

        rebelCreated.getLocations().add(locationCreated);

        for (ItemRebel itemRebel : itemRebelList) {
            itemRebel.setRebel(rebelCreated);
            ItemRebel itemRebelCreated = itemRebelService.createItemRebel(itemRebel);

            rebelCreated.getItemsRebel().add(itemRebelCreated);
        }

        return rebelCreated;
    }

    public Optional<Rebel> findOptionalRebelByUUID(UUID rebelId) {
        return rebelRepository.findByUUID(rebelId);
    }

    public Rebel findRebelByUUID(UUID rebelId) throws RebelNotFoundException {
        return findOptionalRebelByUUID(rebelId).orElseThrow(RebelNotFoundException::new);
    }

    public void incrementRebelAccusedCount(Rebel rebel) {
        rebelRepository.incrementRebelAccusedCount(rebel.getId());
    }

    public long countAllRebels() {
        return rebelRepository.countAllRebels();
    }

    public long countRebels() {
        return rebelRepository.countRebels(Rebel.LIMIT_TRAITOR_DENUNCIATIONS);
    }

    public long countTraitors() {
        return rebelRepository.countTraitors(Rebel.LIMIT_TRAITOR_DENUNCIATIONS);
    }
}
