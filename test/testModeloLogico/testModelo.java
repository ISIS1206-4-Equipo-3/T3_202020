package testModeloLogico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import modeloLogico.Modelo;
class testModelo {

	private Modelo modelo;
	
	public void setUp1()
	{
		modelo = new Modelo();
		modelo.cargarDatosTablaHashLinearProbing(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS);
	
		
	}
	public void setUp2()
	{
		modelo = new Modelo();
		modelo.cargarDatosTablaHashSeparateChaining(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS);
		
	}
	@Test
	void testModelo() {
		setUp1();
		assertNotNull(modelo.tablaLinearProbing);
		
	}

	@Test
	void testCargarDatosTablaHashLinearProbing() {
		setUp1();
		assertNotNull(modelo.tablaLinearProbing);
	}

	@Test
	void testCargarDatosTablaHashSeparateChaining() {
		setUp2();
		assertNotNull(modelo.tablaSeparateChaining);
	}

	@Test
	void testConocerPeliculasAnoProduccionLinearProbing() {
		fail("Not yet implemented");
	}

	@Test
	void testConocerPeliculasAnoProduccionSeparateChaining() {
		fail("Not yet implemented");
	}

}
