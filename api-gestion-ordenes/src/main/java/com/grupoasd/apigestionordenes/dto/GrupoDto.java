package com.grupoasd.gestionempleados.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 *
 * @author vbocanegra
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GrupoDto {

    /**
     * Campo grupo_id.
     */
    private Long grupoId;

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
    private String fechaRegistro;

    /**
     * Campo fecha_modificacion.
     */
    private String fechaModificacion;

    /**
     * lista empleados.
     */
    private List<EmpleadoDto> empleados;

    /**
     * Campo mensaje.
     */
    private String mensaje;

    public GrupoDto() {
    }

    public GrupoDto(String nombre, String direccion, String fechaRegistro, String fechaModificacion, String mensaje) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.mensaje = mensaje;
    }

    public GrupoDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
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

    public List<EmpleadoDto> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoDto> empleados) {
        this.empleados = empleados;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
