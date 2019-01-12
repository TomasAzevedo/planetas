/**
 * 
 */
package br.com.b2w.planetas.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.b2w.planetas.api.model.Planeta;

/**
 * 
 * Acesso ao respositório de plaentas no MongoDB.
 * 
 * @author Tomás Azevedo
 *
 */
public interface PlanetaRepository extends MongoRepository<Planeta, String>, PagingAndSortingRepository<Planeta, String> {
	
	public Optional<Planeta> findByNome(String nome);
    
}
