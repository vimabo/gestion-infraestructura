/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.apigestionordenes.impl;

import com.grupoasd.apigestionordenes.dao.EquipoDao;
import com.grupoasd.apigestionordenes.dao.OrdenDao;
import com.grupoasd.apigestionordenes.dto.EquipoDto;
import com.grupoasd.apigestionordenes.dto.OrdenDto;
import com.grupoasd.apigestionordenes.entity.Equipo;
import com.grupoasd.apigestionordenes.entity.Orden;
import com.grupoasd.apigestionordenes.enumeraciones.EstadoEquipoEnum;
import com.grupoasd.apigestionordenes.service.OrdenService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author vbocanegra
 */
@Service
public class OrdenServiceImpl implements OrdenService {

	@Autowired
	private OrdenDao ordenDao;
        
        
        
        /**
	 * Metodo que retorna listado de todas las ordenes en BD.
	 *
	 * @author Victor Bocanegra
	 * @return List OrdenDto
	 */
	@Override
	public List<OrdenDto> getOrdenes() {

		List<Orden> list = ordenDao.findAll();
		List<OrdenDto> result = new ArrayList<>();
		for (Orden orden : list) {
			OrdenDto dto = new OrdenDto();
			dto.setOrdenId(orden.getOrdenId());
			dto.setDescripcion(orden.getDescripcion());
			dto.setFechaRegistro(orden.getFechaRegistro());
			dto.setFechaModificacion(orden.getFechaModificacion());
			dto.setEstado(orden.getEstado().getId());
			result.add(dto);
		}
		return result;
	}

	/**
	 * Metodo que permite la creacion de un equipo.
	 *
	 * @author Victor Bocanegra
	 * @param equipoDto EquipoDto
	 * @return EquipoDto
	 */
	/*@Override
	public EquipoDto createEquipo(EquipoDto equipoDto) {

		EquipoDto result = null;
		validacionesEquipo(equipoDto, null);
		EstadoEquipoEnum estado = EstadoEquipoEnum.getValueOf(equipoDto.getEstado());
		Equipo entity = new Equipo(equipoDto.getNombre(), equipoDto.getMarca(),
				equipoDto.getCodigoBarras(), new Date(), estado);
		entity = equipoDao.save(entity);
		if (entity != null) {
			result = new EquipoDto();
			result.setEquipoId(entity.getEquipoId());
			result.setNombre(entity.getNombre());
			result.setMarca(entity.getMarca());
			result.setCodigoBarras(entity.getCodigoBarras());
			result.setFechaRegistro(entity.getFechaRegistro());
			result.setEstado(entity.getEstado().getId());
			result.setMensaje("Equipo creado satisfatoriamente");
		} else {
			throw new IllegalArgumentException("Ocurrio un error al momento de crear el equipo");
		}

		return result;
	}

	public void validacionesEquipo(EquipoDto equipoDto, Long idEquipo) {

		if (equipoDto.getNombre() == null || equipoDto.getNombre().isEmpty()) {
			throw new IllegalArgumentException("El Nombre del equipo es obligatorio");

		}
		if (equipoDto.getMarca() == null || equipoDto.getMarca().isEmpty()) {
			throw new IllegalArgumentException("La Marca del equipo es obligatorio");

		}
		if (equipoDto.getCodigoBarras() == null || equipoDto.getCodigoBarras().isEmpty()) {
			throw new IllegalArgumentException("El Codigo de Barra es obligatorio");

		}

		if (equipoDto.getEstado() == null) {
			throw new IllegalArgumentException("El Estado del equipo es obligatorio");

		}

		if (idEquipo == null) {
			Optional<Equipo> consulta = equipoDao.findByCodigoBarras(equipoDto.getCodigoBarras());
			if (!consulta.isEmpty()) {
				throw new IllegalArgumentException(
						"Ya existe un equipo con codigo de barras: " + equipoDto.getCodigoBarras() + "");
			}
		} else {
			Optional<Equipo> consulta = equipoDao.findByCodigoBarrasAndId(equipoDto.getCodigoBarras(), idEquipo);
			if (!consulta.isEmpty()) {
				throw new IllegalArgumentException(
						"Ya existe un equipo con codigo de barras:" + equipoDto.getCodigoBarras() + "");
			}

		}
	}

	/**
	 * Metodo que permite la actualizacion de un equipo.
	 *
	 * @author Victor Bocanegra
	 * @param idEquipo  Long
	 * @param equipoDto EquipoDto
	 * @return EquipoDto
	 */
	/*@Override
	public EquipoDto updateEquipo(Long idEquipo, EquipoDto equipoDto) {

		EquipoDto result = null;
		validacionesEquipo(equipoDto, idEquipo);

		Optional<Equipo> update = equipoDao.findById(idEquipo);

		if (update != null) {
			Equipo save = update.get();
			save.setNombre(equipoDto.getNombre());
			save.setMarca(equipoDto.getMarca());
			save.setCodigoBarras(equipoDto.getCodigoBarras());
			EstadoEquipoEnum estado = EstadoEquipoEnum.getValueOf(equipoDto.getEstado());
			save.setEstado(estado);
			save.setFechaRegistro(equipoDto.getFechaRegistro());
			equipoDao.save(save);
			result = new EquipoDto(save.getEquipoId(), save.getNombre(), save.getMarca(), save.getCodigoBarras(),
					save.getFechaRegistro(), save.getEstado().getId(), "Equipo actualizado satisfatoriamente");

		} else {
			throw new IllegalArgumentException("No existe el equipo en BD");
		}

		return result;
	}*/

	/**
	 * Metodo que retorna listado de todos los equipos en BD.
	 *
	 * @author Victor Bocanegra
	 * @return List EquipoDto
	 */
	/*@Override
	public List<EquipoDto> getEquipos() {

		List<Equipo> list = equipoDao.findAll();
		List<EquipoDto> result = new ArrayList<>();
		for (Equipo equipo : list) {
			EquipoDto dto = new EquipoDto();
			dto.setEquipoId(equipo.getEquipoId());
			dto.setNombre(equipo.getNombre());
			dto.setMarca(equipo.getMarca());
			dto.setCodigoBarras(equipo.getCodigoBarras());
			dto.setFechaRegistro(equipo.getFechaRegistro());
			dto.setEstado(equipo.getEstado().getId());
			result.add(dto);
		}
		return result;
	}

	/**
	 * Metodo que permite la eliminacion de un equipo en BD.
	 *
	 * @author Victor Bocanegra
	 * @param idEquipo Long
	 * @return EquipoDto
	 */
	/*@Override
	public EquipoDto deleteEquipo(Long idEquipo) {
		EquipoDto result = new EquipoDto();
		try {
			equipoDao.deleteById(idEquipo);
			result.setMensaje("Equipo Eliminado satisfatoriamente");
		} catch (EmptyResultDataAccessException ex) {
			result.setMensaje("No existe Equipo con la informaciòn ingresada");
		}
		return result;
	}*/

}
