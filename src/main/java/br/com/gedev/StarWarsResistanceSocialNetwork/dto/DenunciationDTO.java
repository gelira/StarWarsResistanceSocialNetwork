package br.com.gedev.StarWarsResistanceSocialNetwork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class DenunciationDTO {
    private UUID _id;

    @JsonProperty("accused_rebel")
    private RebelNameDTO accusedRebel;

    @JsonProperty("accuser_rebel")
    private RebelNameDTO accuserRebel;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;
}
