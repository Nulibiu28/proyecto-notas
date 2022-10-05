package com.qds.prueba.notas;

import com.qds.prueba.notas.daos.EstudiantesRepository;
import com.qds.prueba.notas.daos.NotasRepository;
import com.qds.prueba.notas.entity.Nota;
import com.qds.prueba.notas.services.Impl.NotasServiceImpl;
import com.qds.prueba.notas.services.NotasService;
import com.qds.prueba.notas.utils.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NotasApplicationTests {

	private NotasService notasService;
	private NotasRepository notasRepository;
	private EstudiantesRepository estudiantesRepository;

	@BeforeEach
	void setUp(){
		notasRepository = mock(NotasRepository.class);
		estudiantesRepository = mock(EstudiantesRepository.class);
		notasService = new NotasServiceImpl(notasRepository, estudiantesRepository);
	}

	@Test
	public void testListNotasxAlumnoNotNull(){
		assertNotNull(notasService.listarNotas(anyLong()));
	}

	@Test
	public void testListNotasxAlumnoWhenIdDontExist(){
		when(estudiantesRepository.findById(5L)).thenReturn(Optional.empty());
		assertEquals(notasService.listarNotas(5L), Collections.singletonMap("response", "No existe un usuario con ese ID"));
	}

	@Test
	public void testListNotasxAlumnoWhenNotaDontExist(){
		when(estudiantesRepository.findById(1L)).thenReturn(Optional.of(Data.ESTUDIANTE_01));
		when(notasRepository.findByEstudiante(Data.ESTUDIANTE_01)).thenReturn(Optional.empty());

		assertEquals(notasService.listarNotas(1L), Collections.singletonMap("response", "No existen notas para este estudiantes"));
	}

	@Test
	public void testListNotasxAlumnoReturnList(){

		List<Nota> notas = List.of(Data.NOTA_01,Data.NOTA_02,Data.NOTA_03);

		when(estudiantesRepository.findById(1L)).thenReturn(Optional.of(Data.ESTUDIANTE_01));
		when(notasRepository.findByEstudiante(Data.ESTUDIANTE_01)).thenReturn(Optional.of(notas));

		assertEquals(notasService.listarNotas(1L), Collections.singletonMap("response", notas));
	}

	@Test
	public void testSaveNotasAlumnoNotEmpty(){
		assertEquals(notasService.guardarNotas(anyLong(), new ArrayList<>()), Collections.singletonMap("response", "Debe ingresar notas para el estudiante"));
	}

}
