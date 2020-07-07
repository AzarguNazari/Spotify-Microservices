package com.imageService.jaxrs.exceptions;

import com.imageService.models.ErrorMessage;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class ClientRequestException extends WebApplicationException {
    public ClientRequestException(ErrorMessage message) {
        super(Response.status(400).entity(message).build());
    }
}
