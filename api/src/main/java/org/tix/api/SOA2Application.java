package org.tix.api;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.tix.api.config.CORSFilter;
import org.tix.api.resource.BookingResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class SOA2Application extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(BookingResource.class);
        classes.add(CORSFilter.class);
        return classes;
    }
}