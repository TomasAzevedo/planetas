/**
 * 
 */
package br.com.b2w.planetas.api.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.b2w.planetas.api.dto.PlanetaDTO;
import br.com.b2w.planetas.api.model.Planeta;
import br.com.b2w.planetas.api.service.PlanetaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


/**
 * 
 * API REST de planetas.
 * 
 * @author Tomás Azevedo
 *
 */
@RestController
@RequestMapping("/planetas")
class PlanetasController {
	
	private static final String MSG_PLANETA_EXISTENTE = "Este planeta já existe.";
	private static final String MSG_ID_INVALIDO = "Para atualizar um planeta é necessário informar o id.";
	
	@Autowired
	private PlanetaService planetaService;
	
	
	@Autowired
    private ModelMapper modelMapper;
	
	
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlanetaDTO> criar(@Valid @RequestBody(required=true) PlanetaDTO planetaDTO) {
		
		Planeta planetaNovo = null; 
		
		try {
			
			planetaNovo = planetaService.criar(converterParaEntidade(planetaDTO));
			
		} catch (DuplicateKeyException dke) {
			
			throw new ResponseStatusException(HttpStatus.CONFLICT, MSG_PLANETA_EXISTENTE, dke);	
			
		}
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planetaNovo.getId()).toUri();
		
		return ResponseEntity.created(location).body(converterParaDTO(planetaNovo));
		
	}
	
	
	
	
	
	
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
	            value = "Retorna a página informada (0..N)"),
	    @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
	            value = "Número de registros por página."),
	    @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
	            value = "Ordenação no formato: nome_atributo(,asc|desc). " +
	                    "Ordenação padrão é asc. " +
	                    "Mais de um critério de ordenação é suportado.")
	})
	@GetMapping(value="/lista", produces = MediaType.APPLICATION_JSON_VALUE)	
	public Page<PlanetaDTO> listar(Pageable pageable) {
		
		Page<Planeta> pagePlaneta = planetaService.listar(pageable);
		
		Page<PlanetaDTO> pagePlanetaDTO = pagePlaneta.map(planeta -> converterParaDTO(planeta));
		
		return pagePlanetaDTO;
		
	}

	
	
	
	
	
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<PlanetaDTO> buscarPeloId(@PathVariable String id) {
		
		Planeta planeta = planetaService.obterPorId(id);
		
		return null == planeta ? ResponseEntity.notFound().build() : ResponseEntity.ok(converterParaDTO(planeta));
		
	}
	
	
	
	
	
	
	
	@GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<PlanetaDTO> buscarPeloNome(@RequestParam("nome") String nome) {
		
		Planeta planeta = planetaService.obterPorNome(nome);
		
		return null==planeta ? ResponseEntity.notFound().build() : ResponseEntity.ok(converterParaDTO(planeta));
		
	}
	
	
	
	
	
	
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlanetaDTO> atualizar(@Valid @RequestBody PlanetaDTO planetaDTO) {
		
		if(null == planetaDTO.getId() || planetaDTO.getId().isEmpty()) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_INVALIDO, new IllegalArgumentException());	
			
		}
		
		Planeta planetaAtualizado = new Planeta();
		
		try {
			
			planetaAtualizado = planetaService.alterar(converterParaEntidade(planetaDTO));
			
		} catch (IllegalArgumentException iae) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iae.getMessage(), iae);	
			
		}
		
		return ResponseEntity.ok(converterParaDTO(planetaAtualizado));
		
	}
	
	
	
	
	
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String id) {
		
		planetaService.excluir(id);
		
	}
	
	

	
	
	private PlanetaDTO converterParaDTO(Planeta planeta) {
		return modelMapper.map(planeta, PlanetaDTO.class);
	}
	
	
	
	
	
	private Planeta converterParaEntidade(PlanetaDTO planetaDTO) {
	    return modelMapper.map(planetaDTO, Planeta.class);
	}
	

}
