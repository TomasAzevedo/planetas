/**
 * 
 */
package br.com.b2w.planetas.api.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

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
	@Indexed(unique=true)
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clima == null) ? 0 : clima.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((terreno == null) ? 0 : terreno.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planeta other = (Planeta) obj;
		if (clima == null) {
			if (other.clima != null)
				return false;
		} else if (!clima.equals(other.clima))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (terreno == null) {
			if (other.terreno != null)
				return false;
		} else if (!terreno.equals(other.terreno))
			return false;
		return true;
	}
	
	

}
