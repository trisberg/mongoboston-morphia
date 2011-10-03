package org.springframework.data.demo.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

@Configuration
@Profile("cloud")
public class CloudConfig {

	@Inject Environment env;
	
	@Bean
	public Datastore datastore(Mongo mongo) { 
		String db = env.getProperty("cloud.services.mongodb.connection.db"); 
		String username =env.getProperty("cloud.services.mongodb.connection.username"); 
		String password = env.getProperty("cloud.services.mongodb.connection.password");
    	Morphia morphia = new Morphia();
		return morphia.createDatastore(mongo, db, username, password.toCharArray());
    }

}
