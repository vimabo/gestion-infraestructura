/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.apigestionordenes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupoasd.apigestionordenes.entity.Orden;


/**
 *
 * @author vbocanegra
 */
public interface OrdenDao extends JpaRepository<Orden, Long> {


}
