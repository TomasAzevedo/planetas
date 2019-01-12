/**
 * 
 */
package br.com.b2w.planetas.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b2w.planetas.api.model.Planeta;
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
	private StarWarService starWarService;
	
	

	@Override
	public Planeta criar(Planeta planeta) {
		
		
		return null;
	}

	
	
	
	
//	@Override
//	public Page<Planeta> listar(Page<Planeta> pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
	
	
	@Override
	public Planeta obterPorId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	@Override
	public Planeta obterPorNome(String nome) {
		Planeta planeta = new Planeta();
		planeta.set_id("1");
		planeta.setNome(nome);
		planeta.setClima("Arid");
		planeta.setTerreno("Dessert");
		planeta.setQtdFilmes(starWarService.obterQuantidadeFilmes(nome));
		return planeta;
	}

	
	
	
	
	@Override
	public Planeta alterar(Planeta planeta) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	@Override
	public void excluir(String id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
