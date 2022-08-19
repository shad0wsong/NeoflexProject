package dossierms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

    @Bean
    public ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }
}
