package com.gustavonalle.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("other")
public class AnotherResource {

    @GET
    @Produces("text/plain")
    public String getOther() {
        return "This is more polite.";

    }
}
