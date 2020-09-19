package me.inonecloud.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CloudAuthStrategy {

    private Map<String, CloudsAuthService> strategies;

    public CloudAuthStrategy(Set<CloudsAuthService> cloudsAuthServices) {
        createStrategy(cloudsAuthServices);
    }

    public CloudsAuthService chooseCloudAuthService(String cloudServiceName){
        return strategies.get(cloudServiceName);
    }

    private void createStrategy(Set<CloudsAuthService> cloudsAuthServices) {
        strategies = new HashMap<>();
        cloudsAuthServices.forEach(cloudsAuthService -> {
            strategies.put(cloudsAuthService.getClass().getSimpleName(), cloudsAuthService);
        });
    }
}
