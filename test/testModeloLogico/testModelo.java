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
		modelo.cargarDatosTablaHashSeparateChaining(modelo.RUTA_DATOS_PRINCIPALES, modelo.RUTA_DATOS_SECUNDARIOS, 2, false);
		
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
	public void testConocerPeliculasAnoProduccionLinearProbing() {
		setUp1();
		assertNotNull(modelo.tablaLinearProbing);
	}

	@Test
	public void testConocerPeliculasAnoProduccionSeparateChaining() {
		setUp1();
		assertEquals(null,modelo.tablaSeparateChaining);
	}

}
