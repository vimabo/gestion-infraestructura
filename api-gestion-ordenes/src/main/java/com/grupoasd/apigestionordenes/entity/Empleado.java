package com.grupoasd.apigestionordenes.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author vbocanegra
 */
@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * identificador de la tabla empleado.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleado_id", updatable = false, nullable = false)
    private long empleadoId;

    /**
     * Campo nombre_completo.
     */
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    /**
     * Campo tipo_documento.
     */
    @Column(name = "tipo_documento")
    private String tipoDocumento;

    /**
     * Campo numero_documento.
     */
    @Column(name = "numero_documento")
    private String numeroDocumento;

    /**
     * Campo email.
     */
    @Column(name = "email")
    private String email;

    /**
     * Campo estado.
     */
    @Column(name = "estado")
    private boolean estado;
    

    @OneToOne(mappedBy = "empleado")
    private Orden orden;

    public Empleado() {
    }

    public Empleado(String nombreCompleto, String tipoDocumento, String numeroDocumento, String email) {
        super();
        this.nombreCompleto = nombreCompleto;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.email = email;
        this.estado = true;
    }

    /**
     * Get the value of userId
     *
     * @return the value of userId
     */
    public long getEmpleadoId() {
        return empleadoId;
    }

    /**
     * Set the value of empleadoId
     *
     * @param empleadoId new value of empleadoId
     */
    public void setEmpleadoId(long empleadoId) {
        this.empleadoId = empleadoId;
    }

    /**
     * Get the value of nombreCompleto
     *
     * @return the value of nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Set the value of nombreCompleto
     *
     * @param nombreCompleto new value of nombreCompleto
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Get the value of tipoDocumento
     *
     * @return the value of tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Set the value of tipoDocumento
     *
     * @param tipoDocumento new value of tipoDocumento
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Get the value of numeroDocumento
     *
     * @return the value of numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Set the value of numeroDocumento
     *
     * @param numeroDocumento new value of numeroDocumento
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of estado
     *
     * @return the value of estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Set the value of estado
     *
     * @param estado new value of estado
     */
    public void setEstado(boolean estado) {
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

}
