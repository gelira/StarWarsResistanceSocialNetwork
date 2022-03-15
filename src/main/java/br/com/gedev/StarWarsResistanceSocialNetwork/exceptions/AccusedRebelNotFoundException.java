package br.com.gedev.StarWarsResistanceSocialNetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "accused rebel not found")
public class AccusedRebelNotFoundException extends Exception {
}
