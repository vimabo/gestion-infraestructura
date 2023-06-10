/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.gestionempleados.impl;


import com.grupoasd.gestionempleados.dao.GrupoDao;
import com.grupoasd.gestionempleados.dto.GrupoDto;
import com.grupoasd.gestionempleados.entity.Grupo;
import com.grupoasd.gestionempleados.service.GrupoService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        validacionesGrupo(grupoDto, null);
        Grupo entity = new Grupo(grupoDto.getNombre(), grupoDto.getDireccion(),
                new Date(), new Date());
        entity = grupoDao.save(entity);
        if (entity != null) {
            result = new GrupoDto();
            result.setGrupoId(entity.getGrupoId());
            result.setNombre(entity.getNombre());
            result.setDireccion(entity.getDireccion());
            result.setFechaRegistro(entity.getFechaRegistro());
            result.setFechaModificacion(entity.getFechaModificacion());
            result.setMensaje("Grupo creado satisfatoriamente");
        } else {
            throw new IllegalArgumentException("Ocurrio un error al momento de crear el grupo");
        }

        return result;
    }

    public void validacionesGrupo(GrupoDto grupoDto, Long idGrupo) {

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
        for (Grupo equipo : list) {
            GrupoDto dto = new GrupoDto();
            dto.setGrupoId(equipo.getGrupoId());
            dto.setNombre(equipo.getNombre());
            dto.setDireccion(equipo.getDireccion());
            dto.setFechaRegistro(equipo.getFechaRegistro());
            dto.setFechaModificacion(equipo.getFechaModificacion());
            result.add(dto);
        }
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
            result.setMensaje("No existe Grupo con la informaciòn ingresada");
        }
        return result;
    }

}
