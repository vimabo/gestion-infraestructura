/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.gestionempleados.impl;

import com.grupoasd.gestionempleados.dao.EmpleadoDao;
import com.grupoasd.gestionempleados.dto.EmpleadoDto;
import com.grupoasd.gestionempleados.entity.Empleado;
import com.grupoasd.gestionempleados.service.EmpleadoService;
import java.util.ArrayList;
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
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoDao empleadoDao;

	/**
	 * Metodo que permite la creacion de un empleado.
	 *
	 * @author Victor Bocanegra
	 * @param empleadoDto EmpleadoDto
	 * @return EmpleadoDto
	 */
	@Override
	public EmpleadoDto createEmpleado(EmpleadoDto empleadoDto) {

		EmpleadoDto result = null;
		validacionesEmpleado(empleadoDto, null);
		Empleado entity = new Empleado(empleadoDto.getNombreCompleto(), empleadoDto.getTipoDocumento(),
				empleadoDto.getNumeroDocumento(), empleadoDto.getEmail());
		entity = empleadoDao.save(entity);
		if (entity != null) {
			result = new EmpleadoDto();
			result.setEmpleadoId(entity.getEmpleadoId());
			result.setNombreCompleto(entity.getNombreCompleto());
			result.setTipoDocumento(entity.getTipoDocumento());
			result.setNumeroDocumento(entity.getNumeroDocumento());
			result.setEmail(entity.getEmail());
			result.setEstado(entity.isEstado());
			result.setMensaje("Empleado creado satisfatoriamente");
		} else {
			throw new IllegalArgumentException("Ocurrio un error al momento de crear el empleado");
		}

		return result;
	}

	public void validacionesEmpleado(EmpleadoDto empleadoDto, Long idEmpleado) {

		if (empleadoDto.getNombreCompleto() == null || empleadoDto.getNombreCompleto().isEmpty()) {
			throw new IllegalArgumentException("El Nombre Completo es obligatorio");

		}
		if (empleadoDto.getTipoDocumento() == null || empleadoDto.getTipoDocumento().isEmpty()) {
			throw new IllegalArgumentException("El Tipo Documento es obligatorio");

		}
		if (empleadoDto.getNumeroDocumento() == null || empleadoDto.getNumeroDocumento().isEmpty()) {
			throw new IllegalArgumentException("El Numero Documento es obligatorio");

		}

		if (empleadoDto.getEmail() == null || empleadoDto.getEmail().isEmpty()) {
			throw new IllegalArgumentException("El Email es obligatorio");

		}

		if (idEmpleado == null) {
			Optional<Empleado> consulta = empleadoDao.findByEmail(empleadoDto.getEmail());
			if (!consulta.isEmpty()) {
				throw new IllegalArgumentException(
						"Ya existe un empleado con el email: " + empleadoDto.getEmail() + "");
			}

			consulta = empleadoDao.findByTipoDocAndNumDoc(empleadoDto.getTipoDocumento(),
					empleadoDto.getNumeroDocumento());
			if (!consulta.isEmpty()) {
				throw new IllegalArgumentException("Ya existe un empleado con tipo de documento: "
						+ empleadoDto.getTipoDocumento() + " y numero: " + empleadoDto.getNumeroDocumento() + "");
			}
		} else {
			Optional<Empleado> consulta = empleadoDao.findByEmailAndId(empleadoDto.getEmail(),
					idEmpleado);
			if (!consulta.isEmpty()) {
				throw new IllegalArgumentException(
						"Ya existe un empleado con el email: " + empleadoDto.getEmail() + "");
			}
			consulta = empleadoDao.findByTipoDocAndNumDocAndId(empleadoDto.getTipoDocumento(),
					empleadoDto.getNumeroDocumento(), idEmpleado);
			if (!consulta.isEmpty()) {
				throw new IllegalArgumentException("Ya existe un empleado con tipo de documento: "
						+ empleadoDto.getTipoDocumento() + " y numero: " + empleadoDto.getNumeroDocumento() + "");
			}
		}
	}

	/**
	 * Metodo que permite la actualizacion de un empleado.
	 *
	 * @author Victor Bocanegra
	 * @param idEmpleado Long
	 * @param empleadoDto EmpleadoDto
	 * @return EmpleadoDto
	 */
	@Override
	public EmpleadoDto updateEmpleado(Long idEmpleado, EmpleadoDto empleadoDto) {

		EmpleadoDto result = null;
		validacionesEmpleado(empleadoDto, idEmpleado);

		Optional<Empleado> update = empleadoDao.findById(idEmpleado);

		if (update != null) {
			Empleado save = update.get();
			save.setNombreCompleto(empleadoDto.getNombreCompleto());
			save.setTipoDocumento(empleadoDto.getTipoDocumento());
			save.setNumeroDocumento(empleadoDto.getNumeroDocumento());
			save.setEmail(empleadoDto.getEmail());
			empleadoDao.save(save);
			result = new EmpleadoDto(save.getEmpleadoId(), save.getNombreCompleto(), save.getTipoDocumento(),
					save.getNumeroDocumento(), save.getEmail(), save.isEstado(),
					"Empleado actualizado satisfatoriamente");

		} else {
			throw new IllegalArgumentException("No existe el empleado en BD");
		}

		return result;
	}

	/**
	 * Metodo que retorna listado de todos los empleados en BD.
	 *
	 * @author Victor Bocanegra
	 * @return List EmpleadoDto
	 */
	@Override
	public List<EmpleadoDto> getEmpleados() {

		List<Empleado> list = empleadoDao.findAll();
		List<EmpleadoDto> result = new ArrayList<>();
		for (Empleado empleado : list) {
			EmpleadoDto dto = new EmpleadoDto();
			dto.setEmpleadoId(empleado.getEmpleadoId());
			dto.setNombreCompleto(empleado.getNombreCompleto());
			dto.setTipoDocumento(empleado.getTipoDocumento());
			dto.setNumeroDocumento(empleado.getNumeroDocumento());
			dto.setEmail(empleado.getEmail());
			dto.setEstado(empleado.isEstado());
			result.add(dto);
		}
		return result;
	}

	/**
	 * Metodo que permite la eliminacion de un empleado en BD.
	 *
	 * @author Victor Bocanegra
	 * @param idEmpleado Long
	 * @return EmpleadoDto
	 */
	@Override
	public EmpleadoDto deleteEmpleado(Long idEmpleado) {
		EmpleadoDto result = new EmpleadoDto();
		try {
			empleadoDao.deleteById(idEmpleado);
			result.setMensaje("Empleado Eliminado satisfatoriamente");
		} catch (EmptyResultDataAccessException ex) {
			result.setMensaje("No existe empleado con la informaciòn ingresada");
		}
		return result;
	}

}
