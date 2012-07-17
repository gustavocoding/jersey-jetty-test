package com.gustavonalle.jetty;

import com.gustavonalle.jersey.AnotherResource;
import com.gustavonalle.jersey.HelloWorldResource;
import com.gustavonalle.jersey.JerseyBuilder;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.handler.HandlerList;

public class JettyServerBuilder {

    public Connector newConnector(int port, String name) {
        SocketConnector socketConnector = new SocketConnector();
        socketConnector.setPort(port);
        socketConnector.setName(name);
        return socketConnector;
    }

    public Server build() {
        HandlerList handlerList = new JerseyBuilder()
                .withResource(HelloWorldResource.class, "connector1")
                .withResource(AnotherResource.class, "connector2")
                .build();

        Server server = new Server();
        server.setConnectors(
                new Connector[]{newConnector(8080, "connector1"),
                        newConnector(8081, "connector2")}
        );

        server.setHandler(handlerList);

        return server;


    }


}
