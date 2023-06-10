/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.gestionempleados.dao;

import com.grupoasd.gestionempleados.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;




/**
 *
 * @author vbocanegra
 */
public interface GrupoDao extends JpaRepository<Grupo, Long> {

}
