package co.edu.udea.iw.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.swing.Spring;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.udea.iw.dto.Ciudad;
import co.edu.udea.iw.exception.MyException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springConfiguration.xml")
public class CiudadDaoImplTest {

	@Autowired
	CiudadDaoImp dao;

	@Test
	public void test1ObtenerCiudades() {
		List<Ciudad> lista = null;
		int total = 1;
		try {
			// Act
			lista = dao.obtener();
			// Assert
			assertTrue(total <= lista.size());
		} catch (MyException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test2ObtenerCiudad() {
		Ciudad ciudad = null;
		int codigoCiudad = 1;
		try {
			// Act
			ciudad = dao.obtener(codigoCiudad);
			// Assert
			assertTrue(ciudad != null);
		} catch (MyException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void test3GuardarCiudad() {
		Ciudad ciudad = null;
		Ciudad ciudadConsulta = null;
		int codigoCiudad = 1;
		int codigo = 9;
		String codigoArea = "19";
		String nombre = "La Ceja";
		try {
			// Act
			ciudad = new Ciudad();
			ciudad.setCodigo(codigo);
			ciudad.setCodigoArea(codigoArea);
			ciudad.setNombre(nombre);
			dao.guardar(ciudad);
			// Assert
			ciudadConsulta = dao.obtener(codigo);
			assertTrue(ciudadConsulta != null);
		} catch (MyException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void test4ActualizarCiudad() {
		Ciudad ciudad = null;
		Ciudad ciudadConsulta = null;
		int codigo = 9;
		String nuevoNombre = "La Ceja del Tambo";
		try {
			// Act
			ciudad = new Ciudad();
			ciudad = dao.obtener(codigo);
			ciudad.setNombre(nuevoNombre);
			dao.actualizar(ciudad);
			// Assert
			ciudadConsulta = dao.obtener(codigo);
			assertTrue(ciudadConsulta.getNombre().equals(nuevoNombre));
		} catch (MyException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test5EliminarCiudad() {
		Ciudad ciudad = null;
		Ciudad ciudadConsulta = null;
		int codigo = 9;
		try {
			// Act
			ciudad = new Ciudad();
			ciudad = dao.obtener(codigo);
			dao.eliminar(ciudad);
			// Assert
			ciudadConsulta = dao.obtener(codigo);
			assertTrue(ciudadConsulta == null);
		} catch (MyException e) {
			fail(e.getMessage());
		}

	}
}
