package gatewayms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TemplateConfig {

    @Bean
    public RestTemplate getTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}
