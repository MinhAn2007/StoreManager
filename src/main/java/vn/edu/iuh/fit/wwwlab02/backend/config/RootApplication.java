package vn.edu.iuh.fit.wwwlab02.backend.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import vn.edu.iuh.fit.wwwlab02.backend.converts.EmployeeStatusConvert;
import vn.edu.iuh.fit.wwwlab02.backend.converts.ProductStatusConvert;
import vn.edu.iuh.fit.wwwlab02.backend.resources.*;
import vn.edu.iuh.fit.wwwlab02.backend.converts.ObjectMapperContextResolver;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RootApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ObjectMapperContextResolver.class);
        classes.add(CustomerResource.class);
        classes.add(ProductResource.class);
        classes.add(EmployeeResource.class);
        classes.add(OrderResource.class);
        classes.add(OrderDetailResource.class);
        classes.add(ProductImageResource.class);
        classes.add(ProductPriceResource.class);
        classes.add(EmployeeStatusConvert.class);
        classes.add(ProductStatusConvert.class);

        return classes;
    }
}