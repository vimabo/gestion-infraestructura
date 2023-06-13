package com.grupoasd.apigestionordenes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

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
    private String fechaRegistro;

    /**
     * Campo fecha_modificacion.
     */
    private String fechaModificacion;

    /**
     * Campo estado.
     */
    private String estado;

    /**
     * Campo equipos.
     */
    private List<EquipoDto> equipos;

    /**
     * Campo empleado_id.
     */
    private long empleadoId;

    /**
     * Campo grupo_id.
     */
    private long grupoId;

    /**
     * Campo mensaje.
     */
    private String mensaje;

    public OrdenDto() {
    }

    public OrdenDto(String descripcion, String fechaRegistro, String fechaModificacion, String estado,
            String mensaje) {
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

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<EquipoDto> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoDto> equipos) {
        this.equipos = equipos;
    }

    public long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(long grupoId) {
        this.grupoId = grupoId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
