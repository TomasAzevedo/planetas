/**
 * 
 */
package br.com.b2w.planetas.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Locale;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.b2w.planetas.api.dto.PlanetaDTO;
import br.com.b2w.planetas.core.app.PlanetasApplication;

/**
 * 
 * Testes integrados
 * 
 * @author Tomás Azevedo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {PlanetasApplication.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlanetasControllerTest {
     
    private MockMvc mockMvc;	
	
//	@Autowired
//    private WebApplicationContext wac;
	
	@Autowired
	private PlanetasController planetasController;
	
	private PlanetaDTO planetaTeste;
	
	private JacksonTester<PlanetaDTO> json;
	
	
    
    @Before
    public void setup() throws Exception {
    	
    	this.mockMvc = standaloneSetup(this.planetasController)
    			.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers(new ViewResolver() {
                    @Override
                    public View resolveViewName(String viewName, Locale locale) throws Exception {
                        return new MappingJackson2JsonView();
                    }
                }).build();
    	
    	ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    	
        planetaTeste = new PlanetaDTO();
        planetaTeste.setNome("Yavin IV");
        planetaTeste.setClima("temperate, tropical");
        planetaTeste.setTerreno("jungle, rainforests");
        
        //Atualiza o id, necessário para alguns testes.
        MvcResult result = mockMvc.perform(get("/planetas/?nome=" + planetaTeste.getNome())).andReturn(); 
		String stringJson = result.getResponse().getContentAsString();
		if(!stringJson.isEmpty()) {
			PlanetaDTO planetaCriado = json.parse(stringJson).getObject();		
			if(null != planetaCriado) planetaTeste.setId(planetaCriado.getId());
		}
    	
    }
    
    
    
    
    @Test
    public void test_00_criar_um_planeta() throws Exception {
    	
    	mockMvc.perform(post("/planetas")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json.write(planetaTeste).getJson()))
			   .andExpect(status().isCreated())
			   .andExpect(jsonPath("nome", is(planetaTeste.getNome())))
			   .andExpect(jsonPath("clima", is(planetaTeste.getClima())))
			   .andExpect(jsonPath("terreno", is(planetaTeste.getTerreno())))
			   .andExpect(jsonPath("id", is(notNullValue())));
    	    	
    }
    

    
    
    @Test
    public void test_01_criar_um_planeta_duplicado() throws Exception {
    	
    	mockMvc.perform(post("/planetas")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json.write(planetaTeste).getJson()))
			   .andExpect(status().isConflict());
    	
    }
    
    
    
    
    @Test
    public void test_02_criar_um_planeta_com_args_invalidos() throws Exception {
    	
    	mockMvc.perform(post("/planetas")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json.write(new PlanetaDTO()).getJson()))
			   .andExpect(status().isBadRequest());
    	
    }
    
    
    
    
    @Test
    public void test_03_criar_um_planeta_sem_args() throws Exception {
    	
    	mockMvc.perform(post("/planetas")
		       .contentType(MediaType.APPLICATION_JSON))
		       //.content(json.write(new Planeta()).getJson()))
			   .andExpect(status().isBadRequest());
    	
    }
    
    
    
    
    @Test
    public void test_04_obter_um_planeta_por_nome() throws Exception {
    	
    	mockMvc.perform(get("/planetas/?nome=" + planetaTeste.getNome()))    		   
               .andExpect(status().isOk())
               .andExpect(jsonPath("nome", is(planetaTeste.getNome())))
			   .andExpect(jsonPath("clima", is(planetaTeste.getClima())))
			   .andExpect(jsonPath("terreno", is(planetaTeste.getTerreno())))
			   .andExpect(jsonPath("id", is(notNullValue())))
			   .andExpect(jsonPath("qtdFilmes", is(notNullValue())));
    	
    }
    
    
    
    
    @Test
    public void test_05_obter_um_planeta_por_nome_inexistente() throws Exception {
    	
    	mockMvc.perform(get("/planetas/?nome=asdasdasd"))    		   
               .andExpect(status().isNotFound());
    	
    }
    
    
    
    
    @Test
    public void test_06_obter_um_planeta_por_id() throws Exception {
    	
    	mockMvc.perform(get("/planetas/" + planetaTeste.getId()))    		   
               .andExpect(status().isOk())
               .andExpect(jsonPath("nome", is(planetaTeste.getNome())))
			   .andExpect(jsonPath("clima", is(planetaTeste.getClima())))
			   .andExpect(jsonPath("terreno", is(planetaTeste.getTerreno())))
			   .andExpect(jsonPath("id", is(notNullValue())))
			   .andExpect(jsonPath("qtdFilmes", is(notNullValue())));
    	
    }
    
    
    
    
    @Test
    public void test_07_obter_um_planeta_por_id_inexistente() throws Exception {
    	
    	mockMvc.perform(get("/planetas/asdasdasd"))    		   
               .andExpect(status().isNotFound());
    	
    }
    
    
    
    
    @Test
    public void test_08_listar_planetas() throws Exception {
    	
    	mockMvc.perform(get("/planetas/lista?page=0&size=50"))    		   
               .andExpect(status().isOk())               
			   .andExpect(jsonPath("content", is(notNullValue())));
			   //.andExpect(jsonPath("totalElements", is(1)));
    	
    }
     
    
    
    
    @Test
    public void test_09_alterar_clima_de_um_planeta() throws Exception {
    	
    	planetaTeste.setClima("novo clima");
    	
    	mockMvc.perform(put("/planetas")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json.write(planetaTeste).getJson()))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("nome", is(planetaTeste.getNome())))
			   .andExpect(jsonPath("clima", is(planetaTeste.getClima())))
			   .andExpect(jsonPath("terreno", is(planetaTeste.getTerreno())))
			   .andExpect(jsonPath("id", is(notNullValue())));
    	    	
    }
        
    
    
    
    @Test
    public void test_10_alterar_um_planeta_inexistente() throws Exception {
    	
    	PlanetaDTO planetaInexistente = new PlanetaDTO();
    	planetaInexistente.setNome("asdasasd dasd d");
    	planetaInexistente.setClima("asd, dsa");
    	planetaInexistente.setTerreno("aaa, asddss");
    	
    	mockMvc.perform(put("/planetas")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(json.write(planetaInexistente).getJson()))
			   .andExpect(status().isBadRequest());
    	    	
    }
    
    
    
    
//    @Test
//    public void test_98_alterar_nome_de_um_planeta() throws Exception {
//    	
//    	planetaTeste.setNome("novo nome");
//    	
//    	mockMvc.perform(put("/planetas")
//		       .contentType(MediaType.APPLICATION_JSON)
//		       .content(json.write(planetaTeste).getJson()))
//			   .andExpect(status().isOk())
//			   .andExpect(jsonPath("nome", is(planetaTeste.getNome())))
//			   .andExpect(jsonPath("clima", is(planetaTeste.getClima())))
//			   .andExpect(jsonPath("terreno", is(planetaTeste.getTerreno())))
//			   .andExpect(jsonPath("id", is(notNullValue())));
//    	    	
//    }
    
    
    
    
    @Test
    public void test_99_excluir_um_planeta() throws Exception {
    	
    	mockMvc.perform(delete("/planetas/" + planetaTeste.getId()))    		   
               .andExpect(status().isNoContent());
    	
    }
 
    // write test cases here
}
