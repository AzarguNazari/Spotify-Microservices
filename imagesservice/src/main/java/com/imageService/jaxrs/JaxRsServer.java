package com.imageService.jaxrs;

import java.net.URI;
import java.util.Properties;

import javax.ws.rs.core.UriBuilder;

import com.imageService.Configuration;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class JaxRsServer {
    private static Properties properties = Configuration.loadProperties();

    public static void main(String[] args) {
        String serverUri = properties.getProperty("restServerUri");

        URI baseUri = UriBuilder.fromUri(serverUri).build();
        ResourceConfig config = ResourceConfig.forApplicationClass(MusicApi.class);
        JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Image Service is running on JAX-RS");
    }
}