package com.searchService.jaxrs;

import java.net.URI;
import java.util.Properties;

import javax.ws.rs.core.UriBuilder;

import com.searchService.Configuration;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class JaxRsServer {
    private static Properties properties = Configuration.loadProperties();

    public static void main(String[] args) {
        String serverUri = properties.getProperty("searchSearch");

        URI baseUri = UriBuilder.fromUri(serverUri).build();
        ResourceConfig config = ResourceConfig.forApplicationClass(MusicApi.class);
        JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Search Service is running on JAX-RS");
    }
}