package vn.edu.iuh.fit.wwwlab02.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import vn.edu.iuh.fit.wwwlab02.converts.ObjectMapperContextResolver;
import vn.edu.iuh.fit.wwwlab02.resources.CustomerResource;
import vn.edu.iuh.fit.wwwlab02.resources.ProductResource;
import vn.edu.iuh.fit.wwwlab02.resources.*;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RootApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ObjectMapperContextResolver.class);
        // Đăng ký các resource của bạn ở đây
        classes.add(CustomerResource.class); // Đăng ký resource của bạn
        classes.add(ProductResource.class); // Đăng ký resource của bạn
        classes.add(EmployeeResource.class); // Đăng ký resource của bạn
        classes.add(OrderResource.class);
        classes.add(OrderDetailResource.class);
        classes.add(ProductImageResource.class);
        classes.add(ProductPriceResource.class);

        return classes;
    }
}