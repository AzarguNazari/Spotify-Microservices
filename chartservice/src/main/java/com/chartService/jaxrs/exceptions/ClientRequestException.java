package com.chartService.jaxrs.exceptions;

import com.chartService.models.ErrorMessage;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class ClientRequestException extends WebApplicationException {
    public ClientRequestException(ErrorMessage message) {
        super(Response.status(400).entity(message).build());
    }
}
