/**
 * 
 */
package br.com.b2w.planetas.swapi.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.planetas.swapi.model.Planet;
import br.com.b2w.planetas.swapi.model.Retorno;

/**
 * 
 * Classe responsável por consumir a API pública SWAPI (Star War).
 * 
 * @author Tomás Azevedo
 *
 */
@Service 
public class StarWarService {
	
	@Value("${api.swapi.url}")
	private String url;
	
	@Value("${api.swapi.planets.search}")
	private String planetsSearch;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private HttpEntity<String> httpEntity;
	
	
	
	public StarWarService() {
		 
         HttpHeaders headers = new HttpHeaders();
         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
         
         httpEntity = new HttpEntity<String>("parameters", headers);

	}
	
	
	
	
	public Integer obterQuantidadeFilmes(String nomePlaneta) {
		
		ResponseEntity<Retorno> response = restTemplate.exchange(url + planetsSearch + nomePlaneta, HttpMethod.GET, httpEntity, Retorno.class);
		
		List<Planet> planets = response.getBody().getResults();
		
		Planet planet = planets.stream()
							   .filter(p -> p.getName().equalsIgnoreCase(nomePlaneta))
							   .findFirst().orElse(null);
		
		return null != planet ? planet.getFilms().size() : 0;
		
	}
	
	
	

}
