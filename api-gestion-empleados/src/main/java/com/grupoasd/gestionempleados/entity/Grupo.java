package com.grupoasd.gestionempleados.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vbocanegra
 */
@Entity
@Table(name = "Grupo")
public class Grupo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * identificador de la tabla grupo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grupo_id", updatable = false, nullable = false)
    private long grupoId;

    /**
     * Campo nombre.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Campo direccion.
     */
    @Column(name = "direccion")
    private String direccion;

    /**
     * Campo fecha_registro.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    /**
     * Campo fecha_modificacion.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @JoinTable(
            name = "empleados_grupos",
            joinColumns = @JoinColumn(name = "grupo_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "empleado_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Empleado> empleados;

    public Grupo() {
    }

    public Grupo(String nombre, String direccion, Date fechaRegistro, Date fechaModificacion, List<Empleado> empleados ) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.empleados = empleados;
    }

    /**
     * Get the value of equipoId
     *
     * @return the value of equipoId
     */
    public long getGrupoId() {
        return grupoId;
    }

    /**
     * Set the value of grupoId
     *
     * @param grupoId new value of grupoId
     */
    public void setGrupoId(long grupoId) {
        this.grupoId = grupoId;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of direccion
     *
     * @return the value of direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Set the value of direccion
     *
     * @param direccion new value of direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Get the value of fechaRegistro
     *
     * @return the value of fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Set the value of fechaRegistro
     *
     * @param fechaRegistro new value of fechaRegistro
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Get the value of fechaModificacion
     *
     * @return the value of fechaModificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * Set the value of fechaModificacion
     *
     * @param fechaModificacion new value of fechaModificacion
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Get the value of empleados
     *
     * @return the value of empleados
     */
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    /**
     * Set the value of empleados
     *
     * @param empleados new value of empleados
     */
    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

}
