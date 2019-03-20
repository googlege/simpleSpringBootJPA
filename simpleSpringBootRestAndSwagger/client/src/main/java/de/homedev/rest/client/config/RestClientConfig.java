package de.homedev.rest.client.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {


    @Bean
    @ConditionalOnClass(RestTemplate.class)
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate()
    {
        final RestTemplate result=new RestTemplate();
        return result;
    }
}
