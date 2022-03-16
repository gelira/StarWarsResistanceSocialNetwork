package br.com.gedev.StarWarsResistanceSocialNetwork.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateDenunciationDTO {
    @JsonProperty("accuser_rebel_id")
    private UUID accuserRebelId;

    @JsonProperty("accused_rebel_id")
    private UUID accusedRebelId;
}
