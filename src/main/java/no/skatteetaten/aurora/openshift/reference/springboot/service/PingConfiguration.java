package no.skatteetaten.aurora.openshift.reference.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.metrics.web.client.MetricsRestTemplateCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 */
@Configuration
public class PingConfiguration {

    @Autowired
    private MetricsRestTemplateCustomizer metricsRestTemplateCustomizer;

    @Bean("pingRestTemplate")
    public RestTemplate restTemplateJson(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
            .rootUri("http://httpbin.org")
            .additionalInterceptors((ClientHttpRequestInterceptor) (request, body, execution) -> {
                request.getHeaders().set("TestHeader", "TestValue");
                return execution.execute(request, body);
            })
            .build();
    }

    @Bean
    public PingService skattemeldingCoreKlient(@Qualifier("pingRestTemplate") RestTemplate restTemplate) {
        return new PingService(restTemplate);
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder()
            .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
            .additionalCustomizers(metricsRestTemplateCustomizer);
    }

}
