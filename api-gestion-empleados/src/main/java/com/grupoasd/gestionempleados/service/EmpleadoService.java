/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.gestionempleados.service;

import java.util.List;
import com.grupoasd.gestionempleados.dto.EmpleadoDto;

/**
 *
 * @author vbocanegra
 */
public interface EmpleadoService {

	/**
	 * Metodo que permite la creacion de un empleado.
	 *
	 * @author Victor Bocanegra
	 * @param empleadoDto EmpleadoDto
	 * @return EmpleadoDto
	 */
	EmpleadoDto createEmpleado(EmpleadoDto empleadoDto);

	/**
	 * Metodo que retorna listado de todos los empleados en BD.
	 *
	 * @author Victor Bocanegra
	 * @return List EmpleadoDto
	 */
	List<EmpleadoDto> getEmpleados();

	/**
	 * Metodo que permite la actualizacion de un empleado.
	 *
	 * @author Victor Bocanegra
	 * @param idEmpleado  Long
	 * @param empleadoDto EmpleadoDto
	 * @return EmpleadoDto
	 */
	EmpleadoDto updateEmpleado(Long idEmpleado, EmpleadoDto empleadoDto);

	/**
	 * Metodo que permite la eliminacion de un empleado en BD.
	 *
	 * @author Victor Bocanegra
	 * @param idEmpleado Long
	 * @return EmpleadoDto
	 */
	EmpleadoDto deleteEmpleado(Long idEmpleado);

}
