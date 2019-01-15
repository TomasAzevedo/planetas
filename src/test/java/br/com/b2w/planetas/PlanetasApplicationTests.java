package br.com.b2w.planetas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.b2w.planetas.core.app.PlanetasApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {PlanetasApplication.class})
public class PlanetasApplicationTests {

	@Test
	public void contextLoads() {
	}

}

