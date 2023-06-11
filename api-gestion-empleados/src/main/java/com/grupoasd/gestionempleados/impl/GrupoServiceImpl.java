/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.gestionempleados.impl;

import com.grupoasd.gestionempleados.dao.EmpleadoDao;
import com.grupoasd.gestionempleados.dao.GrupoDao;
import com.grupoasd.gestionempleados.dto.EmpleadoDto;
import com.grupoasd.gestionempleados.dto.GrupoDto;
import com.grupoasd.gestionempleados.entity.Empleado;
import com.grupoasd.gestionempleados.entity.Grupo;
import com.grupoasd.gestionempleados.service.GrupoService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author vbocanegra
 */
@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoDao grupoDao;

    @Autowired
    private EmpleadoDao empleadoDao;

    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * Metodo que permite la creacion de un grupo.
     *
     * @author Victor Bocanegra
     * @param grupoDto GrupoDto
     * @return GrupoDto
     */
    @Override
    public GrupoDto createGrupo(GrupoDto grupoDto) {

        GrupoDto result = null;
        validacionesGrupo(grupoDto);
        List<Empleado> empleados = null;
        if (grupoDto.getEmpleados() != null && !grupoDto.getEmpleados().isEmpty()) {
            List<Long> filteredList = grupoDto.getEmpleados().stream()
                    .map(EmpleadoDto::getEmpleadoId)
                    .collect(Collectors.toList());

            empleados = empleadoDao.findEmpleadosById(filteredList);
        }

        Grupo entity = new Grupo(grupoDto.getNombre(), grupoDto.getDireccion(),
                new Date(), new Date(), empleados);

        entity = grupoDao.save(entity);
        if (entity != null) {
            result = new GrupoDto();
            result.setGrupoId(entity.getGrupoId());
            result.setNombre(entity.getNombre());
            result.setDireccion(entity.getDireccion());
            result.setFechaRegistro(format.format(entity.getFechaRegistro()));
            result.setFechaModificacion(format.format(entity.getFechaModificacion()));
            result.setMensaje("Grupo creado satisfatoriamente");
        } else {
            throw new IllegalArgumentException("Ocurrio un error al momento de crear el grupo");
        }

        return result;
    }

    public void validacionesGrupo(GrupoDto grupoDto) {

        if (grupoDto.getNombre() == null || grupoDto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El Nombre del grupo es obligatorio");
        }
        if (grupoDto.getDireccion() == null || grupoDto.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La Direccion del grupo es obligatorio");
        }
    }

    /**
     * Metodo que permite la actualizacion de un grupo.
     *
     * @author Victor Bocanegra
     * @param idGrupo Long
     * @param grupoDto GrupoDto
     * @return GrupoDto
     */
    @Override
    public GrupoDto updateGrupo(Long idGrupo, GrupoDto grupoDto) {

        GrupoDto result = null;
        validacionesGrupo(grupoDto);
        Optional<Grupo> update = grupoDao.findById(idGrupo);

        if (update != null) {
            Grupo save = update.get();
            save.setNombre(grupoDto.getNombre());
            save.setDireccion(grupoDto.getDireccion());
            save.setFechaModificacion(new Date());

            List<Empleado> empleados = null;
            if (grupoDto.getEmpleados() != null && !grupoDto.getEmpleados().isEmpty()) {
                List<Long> filteredList = grupoDto.getEmpleados().stream()
                        .map(EmpleadoDto::getEmpleadoId)
                        .collect(Collectors.toList());

                empleados = empleadoDao.findEmpleadosById(filteredList);
            }
            save.setEmpleados(empleados);

            grupoDao.save(save);
            result = new GrupoDto(save.getNombre(), save.getDireccion(),
                    format.format(save.getFechaRegistro()), format.format(save.getFechaModificacion()),
                    "Grupo actualizado satisfatoriamente");

        } else {
            throw new IllegalArgumentException("No existe el Grupo en BD");
        }
        return result;
    }

    /**
     * Metodo que retorna listado de todos los grupos en BD.
     *
     * @author Victor Bocanegra
     * @return List GrupoDto
     */
    @Override
    public List<GrupoDto> getGrupos() {

        List<Grupo> list = grupoDao.findAll();
        List<GrupoDto> result = new ArrayList<>();
        list.stream().map((equipo) -> {
            GrupoDto dto = new GrupoDto();
            dto.setGrupoId(equipo.getGrupoId());
            dto.setNombre(equipo.getNombre());
            dto.setDireccion(equipo.getDireccion());
            dto.setFechaRegistro(format.format(equipo.getFechaRegistro()));
            dto.setFechaModificacion(format.format(equipo.getFechaModificacion()));
            return dto;
        }).forEachOrdered((dto) -> {
            result.add(dto);
        });
        return result;
    }

    /**
     * Metodo que retorna un grupo en BD.
     *
     * @author Victor Bocanegra
     * @param id Long
     * @return GrupoDto
     */
    @Override
    public GrupoDto getGrupo(Long id) {
        GrupoDto dto = null;
        Optional<Grupo> grupo = grupoDao.findById(id);
        if (grupo.isPresent()) {
            dto = new GrupoDto();
            dto.setGrupoId(grupo.get().getGrupoId());
            dto.setNombre(grupo.get().getNombre());
            dto.setDireccion(grupo.get().getDireccion());
            dto.setFechaRegistro(format.format(grupo.get().getFechaRegistro()));
            dto.setFechaModificacion(format.format(grupo.get().getFechaModificacion()));
            dto.setEmpleados(returnListEmpleadosDto(grupo.get().getEmpleados()));
        } else {
            throw new IllegalArgumentException("No existe el grupo en Base de Datos");
        }
        return dto;
    }

    public List<EmpleadoDto> returnListEmpleadosDto(List<Empleado> lista) {

        List<EmpleadoDto> result = new ArrayList<>();
        lista.stream().map((emp) -> {
            EmpleadoDto dto = new EmpleadoDto();
            dto.setEmpleadoId(emp.getEmpleadoId());
            dto.setNombreCompleto(emp.getNombreCompleto());
            dto.setTipoDocumento(emp.getTipoDocumento());
            dto.setNumeroDocumento(emp.getNumeroDocumento());
            dto.setEmail(emp.getEmail());
            dto.setEstado(emp.isEstado() ? "ACTIVO" : "INACTIVO");
            return dto;
        }).forEachOrdered((dto) -> {
            result.add(dto);
        });
        return result;
    }

    /**
     * Metodo que permite la eliminacion de un grupo en BD.
     *
     * @author Victor Bocanegra
     * @param idGrupo Long
     * @return GrupoDto
     */
    @Override
    public GrupoDto deleteGrupo(Long idGrupo) {
        GrupoDto result = new GrupoDto();
        try {
            grupoDao.deleteById(idGrupo);
            result.setMensaje("Grupo Eliminado satisfatoriamente");
        } catch (EmptyResultDataAccessException ex) {
            throw new IllegalArgumentException("No existe Grupo con la informaciòn ingresada");
        }
        return result;
    }

}
