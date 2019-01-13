/**
 * 
 */
package br.com.b2w.planetas.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

/**
 * 
 * Configurações do MongoDB.
 * 
 * @author Tomás Azevedo
 *
 */
@Configuration
@EnableMongoRepositories(basePackages = "br.com.b2w.planetas.api")
public class MongoConfiguration extends AbstractMongoConfiguration {
  
	@Value("${mongo.database}")
	private String database;
	
	@Value("${mongo.host}")
	private String host;
	
	@Value("${mongo.port}")
	private Integer port;
	
	@Value("${mongo.package}")
	private String basePackage;
	
	
    @Override
    protected String getDatabaseName() {
        return "planetas";
    }
    
  
    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host, port);
    }
  
    @Override
    protected String getMappingBasePackage() {
        return basePackage;
    }
    
    
}