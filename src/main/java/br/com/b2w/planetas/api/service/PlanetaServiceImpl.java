/**
 * 
 */
package br.com.b2w.planetas.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.b2w.planetas.api.model.Planeta;
import br.com.b2w.planetas.api.repository.PlanetaRepository;
import br.com.b2w.planetas.swapi.service.StarWarService;

/**
 * 
 * Implementação Jedi da interface PlanetaService.
 * 
 * @author Tomás Azevedo
 *
 */
@Service
public class PlanetaServiceImpl implements PlanetaService {
	
	@Autowired
	private PlanetaRepository planetaRespository;
	
	@Autowired
	private StarWarService starWarService;
	
	

	@Override
	public Planeta criar(Planeta planeta) {
		
		return planetaRespository.insert(planeta);
		
	}

	
	
	
	
	@Override
	public Page<Planeta> listar(Pageable pageable) {
		
		return planetaRespository.findAll(pageable);
		
	}

	
	
	
	
	@Override
	public Planeta obterPorId(String id) {
		
		Planeta planeta = planetaRespository.findById(id).orElse(null);
		
		return planeta;
		
	}

	
	
	
	
	@Override
	public Planeta obterPorNome(String nome) {
		
		Planeta planeta = planetaRespository.findByNome(nome).orElse(null);	
				
		return planeta;
		
	}

	
	
	
	
	@Override
	public Planeta alterar(Planeta planeta) throws IllegalArgumentException {
		
		if(planetaRespository.findById(planeta.getId()).isPresent()) {
		
		planeta.setQtdFilmes(null);
		
		Planeta planetaAtualizado = planetaRespository.save(planeta);
		
		return planetaAtualizado;
		
		} else {
			throw new IllegalArgumentException("Esse planeta não existe.");
		}
		
	}

	
	
	
	
	@Override
	public void excluir(String id) {
		
		planetaRespository.deleteById(id);
		
	}





	@Override
	@Async
	public void atualizarQtdFilmesAsync(Planeta planeta) {
		
		planeta.setQtdFilmes(starWarService.obterQuantidadeFilmes(planeta.getNome()));
		
		planetaRespository.save(planeta);
		
	}
	
	
	
	

}
