package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateItemRebelDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.ItemRebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.enums.ItemsEnum;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.InvalidItemIdException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class ItemRebelBusinessTest {
    @Autowired
    private ItemRebelBusiness itemRebelBusiness;

    List<CreateItemRebelDTO> generateListDTOSuccess(boolean toFail) {
        List<CreateItemRebelDTO> dtoList = new ArrayList<>();

        CreateItemRebelDTO arma1 = new CreateItemRebelDTO();
        arma1.setItemId(ItemsEnum.ARMA.getUUID());
        arma1.setQuantity(10);
        dtoList.add(arma1);

        CreateItemRebelDTO arma2 = new CreateItemRebelDTO();
        arma2.setItemId(ItemsEnum.ARMA.getUUID());
        arma2.setQuantity(5);
        dtoList.add(arma2);

        CreateItemRebelDTO agua = new CreateItemRebelDTO();
        agua.setItemId(ItemsEnum.AGUA.getUUID());
        agua.setQuantity(7);
        dtoList.add(agua);

        CreateItemRebelDTO comida = new CreateItemRebelDTO();
        comida.setItemId(ItemsEnum.COMIDA.getUUID());
        comida.setQuantity(3);
        dtoList.add(comida);

        if (toFail) {
            CreateItemRebelDTO failItem = new CreateItemRebelDTO();
            failItem.setItemId(UUID.randomUUID());
            failItem.setQuantity(3);
            dtoList.add(failItem);
        }

        return dtoList;
    }

    @Test
    void validateAndAggregateItemsSuccessful() throws InvalidItemIdException {
        List<ItemRebel> itemRebelList = itemRebelBusiness.validateAndAggregateItems(
                generateListDTOSuccess(false));

        Assertions.assertThat(itemRebelList).hasSize(4);
        for (ItemRebel itemRebel : itemRebelList) {
            Assertions.assertThat(itemRebel.getItem()).isNotNull();
            Assertions.assertThat(itemRebel.getQuantity()).isGreaterThanOrEqualTo(0);

            UUID itemId = itemRebel.getItem().get_id();

            if (ItemsEnum.ARMA.getUUID().equals(itemId)) {
                Assertions.assertThat(itemRebel.getQuantity()).isEqualTo(15);
            }
            else if (ItemsEnum.AGUA.getUUID().equals(itemId)) {
                Assertions.assertThat(itemRebel.getQuantity()).isEqualTo(7);
            }
            else if (ItemsEnum.COMIDA.getUUID().equals(itemId)) {
                Assertions.assertThat(itemRebel.getQuantity()).isEqualTo(3);
            }
            else if (ItemsEnum.MUNICAO.getUUID().equals(itemId)) {
                Assertions.assertThat(itemRebel.getQuantity()).isEqualTo(0);
            }
            else {
                throw new AssertionError("item id not found");
            }
        }
    }

    @Test
    void validateAndAggregateItemsFail() {
        Assertions.assertThatThrownBy(
                () -> itemRebelBusiness.validateAndAggregateItems(generateListDTOSuccess(true))
        ).isInstanceOf(InvalidItemIdException.class);
    }
}