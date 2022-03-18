package br.com.gedev.StarWarsResistanceSocialNetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "rebel does not have sufficient items to make this deal")
public class InsufficientItemsException extends Exception {
}
