/**
 * 
 */
package br.com.b2w.planetas.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.b2w.planetas.api.model.Planeta;

/**
 * 
 * Que a força esteja com você.
 * 
 * @author Tomás Azevedo
 *
 */
public interface PlanetaService {
	
	
	
	/**
	 * 
	 * Método responsável por criar um planeta.
	 * 
	 * @param planeta - dados do planeta.
	 * 
	 * @return o planeta criado.
	 * 
	 */
	public Planeta criar(Planeta planeta);
	
	
	
	/**
	 * 
	 * Método responsável por listar os planetas. 
	 * 
	 * @param pageable - dados para paginação. 
	 * 
	 * @return a lista de planetas.
	 * 
	 */
	public Page<Planeta> listar(Pageable pageable);
	
	
	
	/**
	 * 
	 * Método responsável por retornar um planeta pelo id.
	 * 
	 * @param id - id do planeta a ser recuperado.
	 * 
	 * @return o planeta solicitado.
	 * 
	 */
	public Planeta obterPorId(String id);
	
	
	
	/**
	 * 
	 * Método responsável por retornar um planeta pelo nome.
	 * 
	 * @param nome - nome do planeta a ser pesquisado.
	 * 
	 * @return o planeta solicitado.
	 * 
	 */
	public Planeta obterPorNome(String nome);
	
	
	
	/**
	 * 
	 * Método responsável por alterar um planeta.
	 * 
	 * @param planeta - dados atualizados.
	 * 
	 * @return o planeta atualizado.
	 * 
	 */
	public Planeta alterar(Planeta planeta) throws IllegalArgumentException;
	
	
	
	/**
	 * 
	 * Método responsável por excluir um planeta.
	 * 
	 * @param id - id do planeta a ser excluído.
	 *  
	 */
	public void excluir(String id);
	
	
	
	
	/**
	 * 
	 * Método assíncrono para atualizar a quantidade de filmes de um determinado planeta.
	 * 
	 * @param planeta - dados do planeta.
	 * 
	 */
	public void atualizarQtdFilmesAsync(Planeta planeta);
	
	

}
