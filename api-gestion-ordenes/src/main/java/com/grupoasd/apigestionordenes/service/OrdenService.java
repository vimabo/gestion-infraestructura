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
}
