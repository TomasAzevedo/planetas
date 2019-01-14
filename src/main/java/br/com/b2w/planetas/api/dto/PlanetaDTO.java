/**
 * 
 */
package br.com.b2w.planetas.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 
 * Data transfer object.
 * 
 * @author Tomás Azevedo
 *
 */
public class PlanetaDTO implements Serializable {

	private static final long serialVersionUID = 6017519265991050965L;
	
	private String id;
	@NotNull
	private String nome;
	@NotNull
	private String clima;
	@NotNull
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
