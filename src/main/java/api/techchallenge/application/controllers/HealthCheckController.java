package api.techchallenge.application.controllers;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

import api.techchallenge.infrastructure.db.DatabaseHealthService;

@RestController
@RequestMapping("/health-check")
@AllArgsConstructor
public class HealthCheckController {
    private final DatabaseHealthService databaseHealthService;


    @GetMapping
    public Map<String, Object> checkHealth() {
        Map<String, Object> healthStatus = new HashMap<>();
        healthStatus.put("db", databaseHealthService.isDatabaseUp());
        healthStatus.put("application", true);

        return healthStatus;
    }
}
