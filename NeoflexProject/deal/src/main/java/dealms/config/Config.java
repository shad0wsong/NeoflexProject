package dealms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        return template;
    }
}
