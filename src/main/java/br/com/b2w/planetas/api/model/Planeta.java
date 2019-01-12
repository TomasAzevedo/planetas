/**
 * 
 */
package br.com.b2w.planetas.api.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

/**
 * 
 * Classe que representa um planeta na API REST.
 * 
 * @author Tomás Azevedo
 *
 */
public class Planeta implements Serializable {

	private static final long serialVersionUID = 2135066040489873225L;
	
	@Id
	private String id;
	private String nome;
	private String clima;
	private String terreno;
	private Integer qtdFilmes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public Integer getQtdFilmes() {
		return qtdFilmes;
	}
	public void setQtdFilmes(Integer qtdFilmes) {
		this.qtdFilmes = qtdFilmes;
	}
	
	

}
