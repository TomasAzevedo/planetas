/**
 * 
 */
package br.com.b2w.planetas.api.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.b2w.planetas.api.model.Planeta;
import br.com.b2w.planetas.core.app.PlanetasApplication;

/**
 * 
 * Testes unitários para conversão de DTO.
 * 
 * @author Tomás Azevedo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {PlanetasApplication.class})
public class PlanetaDTOTest {
	
	@Autowired
	private ModelMapper modelMapper;
	 
    @Test
    public void test_00_converter_para_dto() {
    	
    	Planeta planeta = new Planeta();
    	planeta.setNome("Yavin IV");
    	planeta.setClima("temperate, tropical");
    	planeta.setTerreno("jungle, rainforests");
    	planeta.setQtdFilmes(10);
 
        PlanetaDTO planetaDTO = modelMapper.map(planeta, PlanetaDTO.class);
        
        assertEquals(planetaDTO.getId(), planeta.getId());
        assertEquals(planetaDTO.getNome(), planeta.getNome());
        assertEquals(planetaDTO.getClima(), planeta.getClima());
        assertEquals(planetaDTO.getTerreno(), planeta.getTerreno());
        assertEquals(planetaDTO.getQtdFilmes(), planeta.getQtdFilmes());
        
    }
 
    @Test
    public void test_00_converter_para_entidade() {
    		
    	PlanetaDTO planetaDTO = new PlanetaDTO();
    	planetaDTO.setNome("Yavin IV");
    	planetaDTO.setClima("temperate, tropical");
    	planetaDTO.setTerreno("jungle, rainforests");
    	planetaDTO.setQtdFilmes(10);
 
        Planeta planeta = modelMapper.map(planetaDTO, Planeta.class);
        
        assertEquals(planeta.getId(), planetaDTO.getId());
        assertEquals(planeta.getNome(), planetaDTO.getNome());
        assertEquals(planeta.getClima(), planetaDTO.getClima());
        assertEquals(planeta.getTerreno(), planetaDTO.getTerreno());
        assertEquals(planeta.getQtdFilmes(), planetaDTO.getQtdFilmes());
        
    }

}
