package com.grupoasd.gestionempleados.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.grupoasd.gestionempleados.dto.EmpleadoDto;
import com.grupoasd.gestionempleados.service.EmpleadoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/1.0/empleados")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	/**
	 * Metodo que permite obtener todos los empleados de la BD.
	 *
	 * @author Victor Bocanegra
	 * @return List EmpleadoDto
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<EmpleadoDto>> findAll() {
		try {
			List<EmpleadoDto> list = empleadoService.getEmpleados();
			return ResponseEntity.ok(list);
		} catch (Exception ex) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo que permite la creacion de un empleado.
	 *
	 * @author Victor Bocanegra
	 * @param request EmpleadoDto
	 * @return EmpleadoDto
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<EmpleadoDto> create(@RequestBody EmpleadoDto request) {
		try {
			request = empleadoService.createEmpleado(request);
			return ResponseEntity.ok(request);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(new EmpleadoDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<>(new EmpleadoDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo que permite la eliminacion de un empleado en BD.
	 *
	 * @author Victor Bocanegra
	 * @param empleadoId Long
	 * @return EmpleadoDto
	 */
	@RequestMapping(value = "/delete/{empleadoId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<EmpleadoDto> delete(@PathVariable("empleadoId") long empleadoId) {
		try {
			EmpleadoDto delete = empleadoService.deleteEmpleado(empleadoId);
			return ResponseEntity.ok(delete);
		} catch (EmptyResultDataAccessException ex) {
			return new ResponseEntity<>(new EmpleadoDto(ex.getMessage()), HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(new EmpleadoDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo que permite la actualizacion de un empleado.
	 *
	 * @author Victor Bocanegra
	 * @param empleadoId      empleado a actualizar
	 * @param request EmpleadoDto
	 * @return EmpleadoDto
	 */
	@RequestMapping(value = "/update/{empleadoId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<EmpleadoDto> create(@PathVariable("empleadoId") Long empleadoId,
			@RequestBody EmpleadoDto request) {
		try {
			request = empleadoService.updateEmpleado(empleadoId, request);
			return ResponseEntity.ok(request);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(new EmpleadoDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<>(new EmpleadoDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
