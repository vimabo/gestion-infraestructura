/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.apigestionordenes.service;

import com.grupoasd.apigestionordenes.dto.OrdenDto;
import java.util.List;

/**
 *
 * @author vbocanegra
 */
public interface OrdenService {

    /**
     * Metodo que retorna listado de todas las ordenes en BD.
     *
     * @author Victor Bocanegra
     * @return List OrdenDto
     */
    List<OrdenDto> getOrdenes();

    /**
     * Metodo que permite la creacion de una orden.
     *
     * @author Victor Bocanegra
     * @param ordenDto OrdenDto
     * @return OrdenDto
     */
    OrdenDto createOrden(OrdenDto ordenDto);

    /**
     * Metodo que retorna una orden de la BD.
     *
     * @author Victor Bocanegra
     * @param ordenId Long
     * @return List OrdenDto
     */
    OrdenDto getOrden(Long ordenId);

    /**
     * Metodo que permite la actualizacion de una orden.
     *
     * @author Victor Bocanegra
     * @param idOrden Long
     * @param ordenDto OrdenDto
     * @return OrdenDto
     */
    OrdenDto updateOrden(Long idOrden, OrdenDto ordenDto);
}
