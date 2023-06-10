/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.gestionempleados.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import com.grupoasd.gestionempleados.entity.Empleado;

/**
 *
 * @author vbocanegra
 */
public interface EmpleadoDao extends JpaRepository<Empleado, Long> {

	/**
	 * Metodo que retorna un Empleado a partir de su email.
	 *
	 * @param email del usuario.
	 * @return Optional Empleado
	 * @author vbocanegra
	 */
	Optional<Empleado> findByEmail(String email);

	/**
	 * Metodo que retorna un Empleado a partir de su tipo de documento y numero de
	 * documento.
	 *
	 * @param tipoDoc String.
	 * @param numDoc  String.
	 * @return Optional Empleado
	 * @author vbocanegra
	 */
	@Query("SELECT e FROM Empleado e WHERE e.tipoDocumento = ?1  AND e.numeroDocumento = ?2")
	Optional<Empleado> findByTipoDocAndNumDoc(String tipoDoc, String numDoc);

	/**
	 * Metodo que retorna un Empleado a partir de su email y diferente del id
	 *
	 * @param email del usuario.
	 * @param id    del usuario.
	 * @return Optional Empleado
	 * @author vbocanegra
	 */
	@Query("SELECT e FROM Empleado e WHERE e.email = ?1  AND e.empleadoId != ?2")
	Optional<Empleado> findByEmailAndId(String email, Long id);

	/**
	 * Metodo que retorna un Empleado a partir de su tipo de documento y numero de
	 * documento y Id
	 *
	 * @param tipoDoc String.
	 * @param numDoc  String.
	 * @param id      del usuario.
	 * @return Optional Empleado
	 * @author vbocanegra
	 */
	@Query("SELECT e FROM Empleado e WHERE e.tipoDocumento = ?1  AND e.numeroDocumento = ?2  AND e.empleadoId != ?3")
	Optional<Empleado> findByTipoDocAndNumDocAndId(String tipoDoc, String numDoc, Long id);
}
