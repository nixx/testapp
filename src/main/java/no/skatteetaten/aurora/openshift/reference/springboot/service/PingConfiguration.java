package no.skatteetaten.aurora.openshift.reference.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 */
@Configuration
public class PingConfiguration {

    @Autowired
    public PingConfiguration() {
    }

    @Bean("pingRestTemplate")
    public RestTemplate restTemplateJson() {
        return new RestTemplateBuilder()
            .rootUri("http://httpbin.org")
            .build();
    }

    @Bean
    public PingService skattemeldingCoreKlient(@Qualifier("pingRestTemplate") RestTemplate restTemplate) {
        return new PingService(restTemplate);
    }
}
