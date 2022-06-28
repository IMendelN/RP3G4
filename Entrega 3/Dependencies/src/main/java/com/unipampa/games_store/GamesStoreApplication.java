package com.unipampa.games_store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@RestController
public class GamesStoreApplication {
	
	@Autowired
	
	@Qualifier("applicationInitialUrl")
	

	private String applicationInitialUrl;

	
	@GetMapping("/games")
	public String Games() {
	
		return applicationInitialUrl;
	}

	public static void main(String[] args) {
		SpringApplication.run(GamesStoreApplication.class, args);

	}

}
