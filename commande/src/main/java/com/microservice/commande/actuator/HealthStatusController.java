package com.microservice.commande.actuator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthStatusController {
    @GetMapping("/health-status")
    public String healthStatusPage() {
        return "health-status"; // Refers to health-status.html in the templates folder
    }
}
