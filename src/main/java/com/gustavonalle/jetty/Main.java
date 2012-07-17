package com.gustavonalle.jetty;

import org.eclipse.jetty.server.Server;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new JettyServerBuilder().build();
        server.start();
        server.join();
    }

}
