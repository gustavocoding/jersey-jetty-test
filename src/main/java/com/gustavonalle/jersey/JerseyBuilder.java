package com.gustavonalle.jersey;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Set;

public class JerseyBuilder {

    Multimap<String, Class<?>> resourceMap = HashMultimap.create();

    public JerseyBuilder withResource(Class<?> clazz, String connector) {
        resourceMap.put(connector, clazz);
        return this;
    }

    public HandlerList build() {
        HandlerList handlerList = new HandlerList();
        for (String connector : resourceMap.keySet()) {
            Set<Class<?>> classes = Sets.newHashSet(resourceMap.get(connector));
            ServletContainer servletContainer = new ServletContainer(new DefaultResourceConfig(classes));
            ServletContextHandler servletContextHandler = new ServletContextHandler();
            servletContextHandler.setConnectorNames(new String[]{connector});
            servletContextHandler.addServlet(new ServletHolder(servletContainer), "/rest/*");
            handlerList.addHandler(servletContextHandler);
        }

        return handlerList;
    }


}
