package no.skatteetaten.aurora.openshift.reference.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import no.skatteetaten.aurora.openshift.reference.springboot.service.PingService;

@RestController
public class PingController {

    private PingService pingService;

    @Autowired
    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping("/api/ping")
    public String counter() {
        return pingService.getOrigin();
    }

    @GetMapping("/api/ping2")
    public String counter2() {
        return pingService.getOrigin2();
    }
}
