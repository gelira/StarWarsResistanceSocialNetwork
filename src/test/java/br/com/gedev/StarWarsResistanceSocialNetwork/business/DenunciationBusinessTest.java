package br.com.gedev.StarWarsResistanceSocialNetwork.business;

import br.com.gedev.StarWarsResistanceSocialNetwork.dto.CreateDenunciationDTO;
import br.com.gedev.StarWarsResistanceSocialNetwork.entities.Rebel;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.AccusedRebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.AccuserRebelNotFoundException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.AutoDenunciationException;
import br.com.gedev.StarWarsResistanceSocialNetwork.exceptions.RepeatedDenunciationException;
import br.com.gedev.StarWarsResistanceSocialNetwork.repositories.RebelRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class DenunciationBusinessTest {
    @Autowired
    private DenunciationBusiness denunciationBusiness;

    @Autowired
    private RebelRepository rebelRepository;

    Rebel createRebel() {
        Rebel toCreate = new Rebel();
        toCreate.setName("Test");
        toCreate.setAge(10);
        toCreate.setGenre("human");

        return rebelRepository.save(toCreate);
    }

    @Test
    void createDenunciationFail() {
        Rebel rebel = createRebel();

        CreateDenunciationDTO dto = new CreateDenunciationDTO();
        dto.setAccusedRebelId(rebel.get_id());
        dto.setAccuserRebelId(rebel.get_id());

        Assertions.assertThatThrownBy(() -> denunciationBusiness.createDenunciation(dto))
                .isInstanceOf(AutoDenunciationException.class);

        dto.setAccuserRebelId(UUID.randomUUID());

        Assertions.assertThatThrownBy(() -> denunciationBusiness.createDenunciation(dto))
                .isInstanceOf(AccuserRebelNotFoundException.class);

        dto.setAccuserRebelId(rebel.get_id());
        dto.setAccusedRebelId(UUID.randomUUID());

        Assertions.assertThatThrownBy(() -> denunciationBusiness.createDenunciation(dto))
                .isInstanceOf(AccusedRebelNotFoundException.class);
    }

    @Test
    void createDenunciationSuccessAndRepeatedDenunciationTest()
            throws AccusedRebelNotFoundException, AutoDenunciationException,
            AccuserRebelNotFoundException, RepeatedDenunciationException {

        Rebel accuser = createRebel();
        Rebel accused = createRebel();

        CreateDenunciationDTO dto = new CreateDenunciationDTO();
        dto.setAccusedRebelId(accused.get_id());
        dto.setAccuserRebelId(accuser.get_id());

        denunciationBusiness.createDenunciation(dto);

        rebelRepository.findById(accused.getId()).ifPresent(
                rebel -> Assertions.assertThat(rebel.getAccusedCount()).isEqualTo(1));

        Assertions.assertThatThrownBy(() -> denunciationBusiness.createDenunciation(dto))
                .isInstanceOf(RepeatedDenunciationException.class);
    }
}