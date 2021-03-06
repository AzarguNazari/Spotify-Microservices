package com.searchService.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.searchService.jaxrs.resources.TrackResource;

@ApplicationPath("/")
/**
 * TODO:
 * The API should always consume JSON
 * The API should always respond with JSON
 */
public class MusicApi extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(TrackResource.class);
        return resources;
    }
}
