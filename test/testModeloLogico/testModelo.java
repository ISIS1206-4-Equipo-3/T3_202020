package testModeloLogico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import modeloLogico.Modelo;
public class testModelo {

	private Modelo modelo;
	
	public void setUp1()
	{
		modelo = new Modelo();
		modelo.cargarDatosTablaHashLinearProbing(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS, 0, true);
	
		
	}
	public void setUp2()
	{
		modelo = new Modelo();
		modelo.cargarDatosTablaHashSeparateChaining(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS, 2, false,true);
		
	}
	public void setUp3() {
		modelo = new Modelo();
		modelo.conocerActorReq3("Antonio Banderas");
	}
	
	@Test
	public void testModelo() {
		setUp1();
		assertNotNull(modelo.tablaLinearProbing);
		
	}

	@Test
	public void testCargarDatosTablaHashLinearProbing() {
		setUp1();
		assertNotNull(modelo.tablaLinearProbing);
	}

	@Test
	public void testCargarDatosTablaHashSeparateChaining() {
		setUp2();
		assertNotNull(modelo.tablaSeparateChaining);
	}

	
	@Test
	public void testEntenderGeneroReq4SeparateChaining()
	{
		setUp1();
		assertTrue(!modelo.entenderGenero("Alejandro Alcaraz"));
		assertTrue(modelo.entenderGenero("Drama"));
	}
	@Test
	public void testConocerProductoras() {
		setUp1();
		assertTrue(modelo.conocerProductoras("Columbia Pictures"));
		assertTrue(!modelo.conocerProductoras("Alejandro Alcaraz"));
	}
	
	@Test
	public void testConocerActorReq3SeparateChaining() {
		setUp1();
		assertEquals(true,modelo.conocerActorReq3("Tom Cruise"));
		assertEquals(false,modelo.conocerActorReq3("Santiago Triana"));
	}
	
	@Test
	public void testConocerPaisReq5SeparateChaining() {
		setUp1();
		assertEquals(true,modelo.conocerPeliculasDelPaisReq5("Germany"));
		assertEquals(false,modelo.conocerPeliculasDelPaisReq5("Santiago Triana"));
	}
	

}
