package com.grupoasd.apigestionordenes.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.grupoasd.apigestionordenes.enumeraciones.EstadoOrdenEnum;

/**
 *
 * @author vbocanegra
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdenDto {

    /**
     * Campo orden_id.
     */
    private long ordenId;

    /**
     * Campo descripcion.
     */
    private String descripcion;

    /**
     * Campo fecha_registro.
     */
    private Date fechaRegistro;

    /**
     * Campo fecha_modificacion.
     */
    private Date fechaModificacion;

    /**
     * Campo estado.
     */
    private Long estado;

    /**
     * Campo mensaje.
     */
    private String mensaje;

    public OrdenDto() {
    }

    public OrdenDto(String descripcion, Date fechaRegistro, Date fechaModificacion, Long estado, String mensaje) {
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public OrdenDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(long ordenId) {
        this.ordenId = ordenId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
