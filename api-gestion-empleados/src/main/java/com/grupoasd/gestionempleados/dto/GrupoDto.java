package com.grupoasd.gestionempleados.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author vbocanegra
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GrupoDto {

    /**
     * Campo grupo_id.
     */
    private long grupoId;

    /**
     * Campo nombre.
     */
    private String nombre;

    /**
     * Campo direccion.
     */
    private String direccion;

    /**
     * Campo fecha_registro.
     */
    private Date fechaRegistro;

    /**
     * Campo fecha_modificacion.
     */
    private Date fechaModificacion;

    /**
     * Campo mensaje.
     */
    private String mensaje;

    public GrupoDto() {
    }

    public GrupoDto(String nombre, String direccion, Date fechaRegistro, Date fechaModificacion, String mensaje) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.mensaje = mensaje;
    }

    public GrupoDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(long grupoId) {
        this.grupoId = grupoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
