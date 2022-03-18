package br.com.gedev.StarWarsResistanceSocialNetwork.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "traitor rebel cannot make a deal")
public class DealRebelTraitorException extends Exception {
}
