package br.com.gedev.StarWarsResistanceSocialNetwork.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateLocationDTO {
    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    @NotBlank
    @Size(max = 255)
    @JsonProperty("galaxy_name")
    private String galaxyName;
}
