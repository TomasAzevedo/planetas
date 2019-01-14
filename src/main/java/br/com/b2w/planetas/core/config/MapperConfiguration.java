/**
 * 
 */
package br.com.b2w.planetas.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**	
 * 
 * Configuração do mapper para DTO.
 * 
 * @author Tomás Azevedo
 *
 */
@Configuration
public class MapperConfiguration {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
