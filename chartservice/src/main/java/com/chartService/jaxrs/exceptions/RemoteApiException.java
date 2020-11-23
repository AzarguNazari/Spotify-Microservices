package com.chartService.jaxrs.exceptions;

import com.chartService.models.ErrorMessage;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class RemoteApiException extends WebApplicationException {
    public RemoteApiException(ErrorMessage message) {
        super(Response.status(500).entity(message).build());
    }
}
