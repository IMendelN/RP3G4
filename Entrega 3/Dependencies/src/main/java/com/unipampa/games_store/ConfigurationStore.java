package com.unipampa.games_store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationStore {

    @Bean (name = "applicationName")
    public String applicationName() {
        return "MIGStore";
    
    }

    @Bean (name = "application")
    public String application() {
        applicationName();
        return "MIG Online games store";
    }

    @Bean (name = "applicationInitialUrl")
    public String applicationInitialUrl() {
        return application();
    }

}
