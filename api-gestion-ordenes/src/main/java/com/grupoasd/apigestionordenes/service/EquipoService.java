/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.apigestionordenes.service;

import java.util.List;
import com.grupoasd.apigestionordenes.dto.EquipoDto;

/**
 *
 * @author vbocanegra
 */
public interface EquipoService {

	/**
	 * Metodo que permite la creacion de un equipo.
	 *
	 * @author Victor Bocanegra
	 * @param equipoDto EquipoDto
	 * @return EquipoDto
	 */
	EquipoDto createEquipo(EquipoDto equipoDto);

	/**
	 * Metodo que retorna listado de todos los equipos en BD.
	 *
	 * @author Victor Bocanegra
	 * @return List EquipoDto
	 */
	List<EquipoDto> getEquipos();

	/**
	 * Metodo que permite la actualizacion de un equipo.
	 *
	 * @author Victor Bocanegra
	 * @param idEquipo  Long
	 * @param equipoDto EquipoDto
	 * @return EquipoDto
	 */
	EquipoDto updateEquipo(Long idEquipo, EquipoDto equipoDto);

	/**
	 * Metodo que permite la eliminacion de un equipo en BD.
	 *
	 * @author Victor Bocanegra
	 * @param idEquipo Long
	 * @return EquipoDto
	 */
	EquipoDto deleteEquipo(Long idEquipo);

}
