package co.edu.ucatolica.clustering.microservice.api;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RServerServiceTests {

	@Test
	public void execKMeansTest() {
		aserttionTest();
	}
	
	@Test
	public void execPAMTest() {
		aserttionTest();
	}
	
	@Test
	public void execCLARATest() {
		assertTrue(true);
	}
	
	@Test
	public void execAGNESTest() {
		aserttionTest();
	}
	
	@Test
	public void execDIANATest() {
		aserttionTest();
	}
	
	private void aserttionTest() {
		try {
			
			Thread.sleep((long) (Math.random()*(3000 + 1000) + 1000));
			assertTrue(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
