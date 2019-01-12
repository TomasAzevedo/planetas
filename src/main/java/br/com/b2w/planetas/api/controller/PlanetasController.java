/**
 * 
 */
package br.com.b2w.planetas.api.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.b2w.planetas.api.model.Planeta;
import br.com.b2w.planetas.api.service.PlanetaService;


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
	
	
	@Autowired
	private PlanetaService planetaService;
	
	
	
	
	
	
	
	@PostMapping
	public ResponseEntity<Planeta> criar(@Valid @RequestBody(required=true) Planeta planeta) {
		
		Planeta planetaNovo = planetaService.criar(planeta);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planetaNovo.getId()).toUri();
		
		return ResponseEntity.created(location).body(planeta);
		
	}
	
	
	
	
	
	
	
	@GetMapping(value="/lista")	
	public Page<Planeta> listar(Pageable pageable) {
		
		return planetaService.listar(pageable);
		
	}

	
	
	
	
	
	
	@GetMapping("/{id}")
	public  ResponseEntity<Planeta> buscarPeloId(@PathVariable String id) {
		
		Planeta planeta = planetaService.obterPorId(id);
		
		return null == planeta ? ResponseEntity.notFound().build() : ResponseEntity.ok(planeta);
		
	}
	
	
	
	
	
	
	
	@GetMapping("/")
	public  ResponseEntity<Planeta> buscarPeloNome(@RequestParam("nome") String nome) {
		
		Planeta planeta = planetaService.obterPorNome(nome);
		
		return null==planeta ? ResponseEntity.notFound().build() : ResponseEntity.ok(planeta);
		
	}
	
	
	
	
	
	
	
	@PutMapping
	public ResponseEntity<Planeta> atualizar(@Valid @RequestBody Planeta planeta) {
		
		Planeta planetaAtualizado = planetaService.alterar(planeta);
		
		return ResponseEntity.ok(planetaAtualizado);
		
	}
	
	
	
	
	
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable String id) {
		
		planetaService.excluir(id);
		
	}
	
	

	
	
	

}
