package com.grupoasd.apigestionordenes.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.grupoasd.apigestionordenes.enumeraciones.EstadoOrdenEnum;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

/**
 *
 * @author vbocanegra
 */
@Entity
@Table(name = "Orden")
public class Orden implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * identificador de la tabla orden.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orden_id", updatable = false, nullable = false)
    private long ordenId;

    /**
     * Campo descripcion.
     */
    @Column(name = "descripcion")
    private String descripcion;

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

    /**
     * Campo estado.
     */
    @Column(name = "estado")
    @Enumerated(value = EnumType.ORDINAL)
    private EstadoOrdenEnum estado;

    /**
     * Campo empleado_id.
     */
    @Column(name = "empleado_id")
    private Long empleado;

    /**
     * Campo grupo_id.
     */
    @Column(name = "grupo_id")
    private Long grupo;
    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden")
    private List<Equipo> equipos;

    public Orden() {
    }

    public Orden(String descripcion, EstadoOrdenEnum estado,
            Date fechaRegistro, Date fechaModificacion,
            Long empleado, Long grupo) {
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.estado = estado;
        this.empleado = empleado;
        this.grupo = grupo;
    }

    /**
     * Get the value of ordenId
     *
     * @return the value of ordenId
     */
    public long getOrdenId() {
        return ordenId;
    }

    /**
     * Set the value of ordenId
     *
     * @param ordenId new value of ordenId
     */
    public void setOrdenId(long ordenId) {
        this.ordenId = ordenId;
    }

    /**
     * Get the value of descripcion
     *
     * @return the value of descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Set the value of descripcion
     *
     * @param descripcion new value of descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * Get the value of estado
     *
     * @return the value of estado
     */
    public EstadoOrdenEnum getEstado() {
        return estado;
    }

    /**
     * Set the value of estado
     *
     * @param estado new value of estado
     */
    public void setEstado(EstadoOrdenEnum estado) {
        this.estado = estado;
    }

    /**
     * Get the value of empleado
     *
     * @return the value of empleado
     */
    public Long getEmpleado() {
        return empleado;
    }

    /**
     * Set the value of empleado
     *
     * @param empleado new value of empleado
     */
    public void setEmpleado(Long empleado) {
        this.empleado = empleado;
    }

    /**
     * Get the value of grupo
     *
     * @return the value of grupo
     */
    public Long getGrupo() {
        return grupo;
    }

    /**
     * Set the value of grupo
     *
     * @param grupo new value of grupo
     */
    public void setGrupo(Long grupo) {
        this.grupo = grupo;
    }

    /**
     * Get the value of grupo
     *
     * @return the value of grupo
     */
    public List<Equipo> getEquipos() {
        return equipos;
    }

    /**
     * Set the value of equipos
     *
     * @param equipos new value of equipos
     */
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

}
