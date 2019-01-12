/**
 * 
 */
package br.com.b2w.planetas.swapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * Classe que representa o objeto de retorno da pesquisa feita na SW API.
 * 
 * @author Tomás Azevedo
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Retorno {

	public List<Planet> results;	

	public Retorno() {
		
	}

	public Retorno(List<Planet> results) {
		this.results = results;
	}

	public List<Planet> getResults() {
		return results;
	}

	public void setResults(List<Planet> results) {
		this.results = results;
	}	
	
}
