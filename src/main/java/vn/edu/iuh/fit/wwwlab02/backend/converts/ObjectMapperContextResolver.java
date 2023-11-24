package vn.edu.iuh.fit.wwwlab02.backend.converts;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;

public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
    final ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapperContextResolver() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.findAndRegisterModules();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return null;
    }
}