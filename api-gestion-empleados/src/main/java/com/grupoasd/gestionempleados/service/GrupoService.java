/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.gestionempleados.service;

import com.grupoasd.gestionempleados.dto.GrupoDto;
import java.util.List;

/**
 *
 * @author vbocanegra
 */
public interface GrupoService {

    /**
     * Metodo que permite la creacion de un grupo.
     *
     * @author Victor Bocanegra
     * @param grupoDto GrupoDto
     * @return GrupoDto
     */
    GrupoDto createGrupo(GrupoDto grupoDto);

    /**
     * Metodo que retorna un grupo en BD.
     *
     * @author Victor Bocanegra
     * @param id Long
     * @return GrupoDto
     */
    GrupoDto getGrupo(Long id);

    /**
     * Metodo que retorna listado de todos los grupos en BD.
     *
     * @author Victor Bocanegra
     * @return List GrupoDto
     */
    List<GrupoDto> getGrupos();

    /**
     * Metodo que permite la actualizacion de un grupo.
     *
     * @author Victor Bocanegra
     * @param idGrupo Long
     * @param grupoDto GrupoDto
     * @return GrupoDto
     */
    GrupoDto updateGrupo(Long idGrupo, GrupoDto grupoDto);

    /**
     * Metodo que permite la eliminacion de un grupo en BD.
     *
     * @author Victor Bocanegra
     * @param idGrupo Long
     * @return GrupoDto
     */
    GrupoDto deleteGrupo(Long idGrupo);

}
