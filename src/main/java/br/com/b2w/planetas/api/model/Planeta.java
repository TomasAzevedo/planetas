/**
 * 
 */
package br.com.b2w.planetas.api.model;

import java.io.Serializable;

/**
 * 
 * Classe que representa um planeta na API REST.
 * 
 * @author Tomás Azevedo
 *
 */
public class Planeta implements Serializable {

	private static final long serialVersionUID = 2135066040489873225L;
	
	private String _id;
	private String nome;
	private String clima;
	private String terreno;
	private Integer qtdFilmes;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
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
