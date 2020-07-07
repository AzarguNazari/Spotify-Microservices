package com.chartService.jaxrs;

import com.chartService.Configuration;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Properties;

public class JaxRsServer {
    private static Properties properties = Configuration.loadProperties();

    public static void main(String[] args) {
        String serverUri = properties.getProperty("restServerUri");

        URI baseUri = UriBuilder.fromUri(serverUri).build();
        ResourceConfig config = ResourceConfig.forApplicationClass(MusicApi.class);
        JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Server ready to serve your JAX-RS requests...");
    }
}