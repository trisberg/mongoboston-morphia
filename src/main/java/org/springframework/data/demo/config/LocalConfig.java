package org.springframework.data.demo.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

@Configuration
@Profile("default")
@PropertySource("classpath:local-mongo.properties")
public class LocalConfig {
	
	@Inject Environment env;
	
	@Bean
	public Datastore datastore(Mongo mongo) { 
		String db = env.getProperty("cloud.services.mongodb.connection.db"); 
    	Morphia morphia = new Morphia();
		return morphia.createDatastore(mongo, db);
    }

}
