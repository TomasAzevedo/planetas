/**
 * 
 */
package br.com.b2w.planetas.core.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * Classe de configuração do RestTemplate do Spring.
 * 
 * @author Tomás Azevedo
 *
 */
@Configuration
public class RestConfiguration {
	
	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
