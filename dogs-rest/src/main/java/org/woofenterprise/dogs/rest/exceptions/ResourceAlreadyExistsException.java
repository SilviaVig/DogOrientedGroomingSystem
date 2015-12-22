package org.woofenterprise.dogs.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Silvia.Vigasova
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "The resource already exists.")
public class ResourceAlreadyExistsException extends RuntimeException {
}
