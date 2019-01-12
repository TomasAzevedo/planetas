/**
 * 
 */
package br.com.b2w.planetas.core.config;

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
  
    @Override
    protected String getDatabaseName() {
        return "planetas";
    }
  
    @Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1", 27017);
    }
  
    @Override
    protected String getMappingBasePackage() {
        return "br.com.b2w.planetas.api";
    }
    
}