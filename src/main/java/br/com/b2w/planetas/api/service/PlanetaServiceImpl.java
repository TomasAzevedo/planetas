/**
 * 
 */
package br.com.b2w.planetas.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		
		Page<Planeta> page = planetaRespository.findAll(pageable);
		
		page.getContent().forEach(planeta -> {
			planeta.setQtdFilmes(starWarService.obterQuantidadeFilmes(planeta.getNome()));
		});
		
		return page;
		
	}

	
	
	
	
	@Override
	public Planeta obterPorId(String id) {
		
		Planeta planeta = planetaRespository.findById(id).orElse(null);
		
		if(null != planeta) {
			planeta.setQtdFilmes(starWarService.obterQuantidadeFilmes(planeta.getNome()));
		}
		
		return planeta;
		
	}

	
	
	
	
	@Override
	public Planeta obterPorNome(String nome) {
		
		Planeta planeta = planetaRespository.findByNome(nome).orElse(null);
		
		if(null != planeta) {
			planeta.setQtdFilmes(starWarService.obterQuantidadeFilmes(planeta.getNome()));
		}
				
		return planeta;
		
	}

	
	
	
	
	@Override
	public Planeta alterar(Planeta planeta) {
		
		Planeta planetaAtualizado = planetaRespository.save(planeta);
		
		return planetaAtualizado;
		
	}

	
	
	
	
	@Override
	public void excluir(String id) {
		
		planetaRespository.deleteById(id);
		
	}
	
	
	
	

}
