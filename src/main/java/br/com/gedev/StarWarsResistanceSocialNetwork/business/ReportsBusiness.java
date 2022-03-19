package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.ItemAverageDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.dto.ReportDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.enums.ItemsEnum;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.ItemRebelService;
import br.com.gedev.StarWarsResistanceSocialNetwork.services.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class ReportsBusiness {
    private final RebelService rebelService;
    private final ItemRebelService itemRebelService;

    public ReportDTO generateReport() {
        ReportDTO dto = new ReportDTO();

        calcPercentages(dto);
        calcPointsLostByTraitors(dto);
        calcAverageItems(dto);

        return dto;
    }

    private void calcPercentages(ReportDTO dto) {
        double rebelsPercentage = 0;
        double traitorsPercentage = 0;

        double countAllRebels = rebelService.countAllRebels();

        if (countAllRebels > 0) {
            rebelsPercentage = (rebelService.countRebels() / countAllRebels) * 100;
            traitorsPercentage = (rebelService.countTraitors() / countAllRebels) * 100;
        }

        dto.setRebelsPercentage(rebelsPercentage);
        dto.setTraitorsPercentage(traitorsPercentage);
    }

    private void calcPointsLostByTraitors(ReportDTO dto) {
        int totalPoints = 0;

        for (ItemRebel itemRebel : itemRebelService.findItemsTraitors()) {
            totalPoints += itemRebel.getQuantity() * itemRebel.getItem().getValue();
        }

        dto.setPointsLostByTraitors(totalPoints);
    }

    private void calcAverageItems(ReportDTO dto) {
        Map<UUID, ItemAverageDTO> itemsMap = new HashMap<>();

        for (ItemsEnum itemEnum : ItemsEnum.values()) {
            UUID itemId = itemEnum.getUUID();

            ItemAverageDTO itemDTO = new ItemAverageDTO();
            itemDTO.setItemId(itemId);
            itemDTO.setItemName(itemEnum.getItemName());
            itemDTO.setItemCount(0);
            itemDTO.setRebelCount(0);

            itemsMap.put(itemId, itemDTO);
        }

        for (ItemRebel itemRebel : itemRebelService.findItemsRebels()) {
            UUID itemId = itemRebel.getItem().get_id();

            if (!itemsMap.containsKey(itemId)) {
                continue;
            }

            ItemAverageDTO itemDTO = itemsMap.get(itemId);
            itemDTO.setItemCount(itemDTO.getItemCount() + itemRebel.getQuantity());
            itemDTO.setRebelCount(itemDTO.getRebelCount() + 1);
        }

        List<ItemAverageDTO> dtoList = new ArrayList<>();

        for (Map.Entry<UUID, ItemAverageDTO> mapItem : itemsMap.entrySet()) {
            ItemAverageDTO dtoItem = mapItem.getValue();

            double average = 0;

            if (dtoItem.getRebelCount() > 0) {
                average = (double) dtoItem.getItemCount() / (double) dtoItem.getRebelCount();
            }

            dtoItem.setAveragePerRebel(average);
            dtoList.add(dtoItem);
        }

        dto.setAverageItems(dtoList);
    }
}
