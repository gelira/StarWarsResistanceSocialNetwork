package br.com.gedev.StarWarsResistanceSocialNetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "rebel cannot accuse itself")
public class AutoDenunciationException extends Exception {
}
