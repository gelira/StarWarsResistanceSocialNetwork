package br.com.gedev.StarWarsResistanceSocialNetwork.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDealDTO {
    @JsonProperty("rebel1_id")
    @NotNull
    private UUID rebel1Id;

    @JsonProperty("rebel2_id")
    @NotNull
    private UUID rebel2Id;

    @JsonProperty("rebel1_items")
    @NotEmpty
    @NotNull
    private List<@Valid CreateItemDealDTO> rebel1Items;

    @JsonProperty("rebel2_items")
    @NotEmpty
    @NotNull
    private List<@Valid CreateItemDealDTO> rebel2Items;
}
