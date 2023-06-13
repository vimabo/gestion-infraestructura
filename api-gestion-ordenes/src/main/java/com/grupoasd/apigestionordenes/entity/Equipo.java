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
import com.grupoasd.apigestionordenes.enumeraciones.EstadoEquipoEnum;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author vbocanegra
 */
@Entity
@Table(name = "Equipo")
public class Equipo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * identificador de la tabla equipo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipo_id", updatable = false, nullable = false)
    private long equipoId;

    /**
     * Campo nombre.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Campo marca.
     */
    @Column(name = "marca")
    private String marca;

    /**
     * Campo codigo_barras.
     */
    @Column(name = "codigo_barras")
    private String codigoBarras;

    /**
     * Campo fecha_registro.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    /**
     * Campo estado.
     */
    @Column(name = "estado")
    @Enumerated(value = EnumType.ORDINAL)
    private EstadoEquipoEnum estado;

    /**
     * Campo estado.
     */
    @Column(name = "estado_original")
    @Enumerated(value = EnumType.ORDINAL)
    private EstadoEquipoEnum estadoOriginal;

    /**
     * Campo orden_id.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_id", referencedColumnName = "orden_id")
    private Orden orden;

    public Equipo() {
    }

    public Equipo(String nombre, String marca, String codigoBarras, Date fechaRegistro, EstadoEquipoEnum estado) {
        super();
        this.nombre = nombre;
        this.marca = marca;
        this.codigoBarras = codigoBarras;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.estadoOriginal = estado;
    }

    /**
     * Get the value of equipoId
     *
     * @return the value of equipoId
     */
    public long getEquipoId() {
        return equipoId;
    }

    /**
     * Set the value of userId
     *
     * @param equipoId new value of equipoId
     */
    public void setEquipoId(long equipoId) {
        this.equipoId = equipoId;
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
     * Get the value of marca
     *
     * @return the value of marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Set the value of marca
     *
     * @param marca new value of marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Get the value of codigoBarras
     *
     * @return the value of codigoBarras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * Set the value of codigoBarras
     *
     * @param codigoBarras new value of codigoBarras
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
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
     * Get the value of equipoId
     *
     * @return the value of equipoId
     */
    public EstadoEquipoEnum getEstado() {
        return estado;
    }

    /**
     * Set the value of estado
     *
     * @param estado new value of estado
     */
    public void setEstado(EstadoEquipoEnum estado) {
        this.estado = estado;
    }

    /**
     * Get the value of orden
     *
     * @return the value of orden
     */
    public Orden getOrden() {
        return orden;
    }

    /**
     * Set the value of orden
     *
     * @param orden new value of orden
     */
    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    /**
     * Get the value of estadoOriginal
     *
     * @return the value of estadoOriginal
     */
    public EstadoEquipoEnum getEstadoOriginal() {
        return estadoOriginal;
    }

    /**
     * Set the value of estadoOriginal
     *
     * @param estadoOriginal new value of estadoOriginal
     */
    public void setEstadoOriginal(EstadoEquipoEnum estadoOriginal) {
        this.estadoOriginal = estadoOriginal;
    }

}
