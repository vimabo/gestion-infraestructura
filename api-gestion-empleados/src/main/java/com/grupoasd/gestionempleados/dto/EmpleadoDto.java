package com.grupoasd.gestionempleados.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author vbocanegra
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpleadoDto {

    /**
     * Campo empleado_id.
     */
    private long empleadoId;

    /**
     * Campo nombre_completo.
     */
    private String nombreCompleto;

    /**
     * Campo tipo_documento.
     */
    private String tipoDocumento;

    /**
     * Campo numero_documento.
     */
    private String numeroDocumento;

    /**
     * Campo email.
     */
    private String email;

    /**
     * Campo estado.
     */
    private String estado;

    /**
     * Campo mensaje.
     */
    private String mensaje;

    public EmpleadoDto() {
    }

    public EmpleadoDto(long empleadoId, String nombreCompleto, String tipoDocumento, String numeroDocumento,
            String email, String estado, String mensaje) {
        super();
        this.empleadoId = empleadoId;
        this.nombreCompleto = nombreCompleto;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.email = email;
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public EmpleadoDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
