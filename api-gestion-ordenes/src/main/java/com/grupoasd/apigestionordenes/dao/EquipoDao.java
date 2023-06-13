/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.apigestionordenes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grupoasd.apigestionordenes.entity.Equipo;
import com.grupoasd.apigestionordenes.enumeraciones.EstadoEquipoEnum;
import java.util.List;

import java.util.Optional;

/**
 *
 * @author vbocanegra
 */
public interface EquipoDao extends JpaRepository<Equipo, Long> {

    /**
     * Metodo que retorna un equipo a partir de su codigo de barras.
     *
     * @param codigo del equipo.
     * @return Optional Equipo
     * @author vbocanegra
     */
    Optional<Equipo> findByCodigoBarras(String codigo);

    /**
     * Metodo que retorna un Equipo a partir de su codigo de barras y diferente
     * del id
     *
     * @param codigo del equipo.
     * @param id del Equipo.
     * @return Optional Equipo
     * @author vbocanegra
     */
    @Query("SELECT eq FROM Equipo eq WHERE eq.codigoBarras = ?1  AND eq.equipoId != ?2")
    Optional<Equipo> findByCodigoBarrasAndId(String codigo, Long id);

    /**
     * Metodo que retorna lista de Equipos disponibles
     *
     * @param disponibles List EstadoEquipoEnum
     * @return Optional Equipo
     * @author vbocanegra
     */
    @Query("SELECT eq FROM Equipo eq WHERE eq.estado IN ?1 AND eq.orden.ordenId IS NULL")
    List<Equipo> findByEstados(List<EstadoEquipoEnum> disponibles);

    /**
     * Metodo que retorna una Lista de Equipos a partir de una lista de ids
     *
     * @param ids List long
     * @return Lista Equipo
     * @author vbocanegra
     */
    @Query("SELECT eq FROM Equipo eq WHERE eq.equipoId IN ?1")
    List<Equipo> findEquiposById(List<Long> ids);

    /**
     * Metodo que retorna una Lista de Equipos a partir del id de una orden
     *
     * @param ordenId Long
     * @return Lista Equipo
     * @author vbocanegra
     */
    @Query("SELECT eq FROM Equipo eq WHERE eq.orden.ordenId = ?1")
    List<Equipo> findEquiposByOrdenId(Long ordenId);

}
