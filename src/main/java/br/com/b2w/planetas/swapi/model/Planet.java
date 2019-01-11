/**
 * 
 */
package br.com.b2w.planetas.swapi.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Classe que representa um planeta obtido através da WSAPI.
 * 
 * @author Tomás Azevedo
 *
 */
public class Planet implements Serializable {

	private static final long serialVersionUID = -5697531627816776743L;
	
	private String name;
	private List<String> films;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getFilms() {
		return films;
	}
	public void setFilms(List<String> films) {
		this.films = films;
	}	

}
