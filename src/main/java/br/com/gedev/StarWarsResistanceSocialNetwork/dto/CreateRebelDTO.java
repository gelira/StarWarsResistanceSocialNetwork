package br.com.gedev.StarWarsResistanceSocialNetwork.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRebelDTO {
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    @Min(0)
    private Integer age;

    @NotNull
    @Pattern(regexp = "human|gree|rakata", message = "must be 'human', 'gree' or 'rakata'")
    private String genre;
}
