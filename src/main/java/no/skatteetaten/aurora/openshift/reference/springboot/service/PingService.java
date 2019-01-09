package no.skatteetaten.aurora.openshift.reference.springboot.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import no.skatteetaten.aurora.openshift.reference.springboot.model.GetResponse;

@Service
public class PingService {

    private static final Logger logger = LoggerFactory.getLogger(PingService.class);
    private RestTemplate restTemplate;

    public PingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getOrigin() {
        ResponseEntity<GetResponse> resp = restTemplate.getForEntity("/get", GetResponse.class);
        GetResponse g = Objects.requireNonNull(resp.getBody());
        logger.info("Headers: {}", g.getHeaders());
        return g.getOrigin();
    }

    public String getOrigin2() {
        ResponseEntity<GetResponse> resp = restTemplate.getForEntity("http://httpbin.org/get", GetResponse.class);
        GetResponse g = Objects.requireNonNull(resp.getBody());
        logger.info("Headers: {}", g.getHeaders());
        return g.getOrigin();
    }
}
