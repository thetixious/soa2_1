package org.tix.soa2_1;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.tix.soa2_1.resource.BookingResource;
import org.tix.soa2_1.exception.InvalidParameterExceptionMapper;
import org.tix.soa2_1.service.CORSFilter;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class SOAApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(BookingResource.class);
        classes.add(InvalidParameterExceptionMapper.class);
        classes.add(CORSFilter.class);
        return classes;
    }
}