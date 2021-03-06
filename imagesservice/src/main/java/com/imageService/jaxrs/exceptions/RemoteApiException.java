package com.imageService.jaxrs.exceptions;

import com.imageService.models.ErrorMessage;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class RemoteApiException extends WebApplicationException {
    public RemoteApiException(ErrorMessage message) {
        super(Response.status(500).entity(message).build());
    }
}
