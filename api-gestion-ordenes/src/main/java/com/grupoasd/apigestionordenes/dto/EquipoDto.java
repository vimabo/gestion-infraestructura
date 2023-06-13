package com.grupoasd.apigestionordenes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author vbocanegra
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipoDto {

    /**
     * Campo equipo_id.
     */
    private long equipoId;

    /**
     * Campo nombre.
     */
    private String nombre;

    /**
     * Campo marca.
     */
    private String marca;

    /**
     * Campo codigo_barras.
     */
    private String codigoBarras;

    /**
     * Campo fecha_registro.
     */
    private String fechaRegistro;

    /**
     * Campo estado.
     */
    private String estado;

    /**
     * Campo orden_id.
     */
    private long ordenId;

    /**
     * Campo mensaje.
     */
    private String mensaje;

    public EquipoDto() {
    }

    public EquipoDto(long equipoId, String nombre, String marca, String codigoBarras, String fechaRegistro,
            String estado, String mensaje) {
        super();
        this.equipoId = equipoId;
        this.nombre = nombre;
        this.marca = marca;
        this.codigoBarras = codigoBarras;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public EquipoDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public long getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(long equipoId) {
        this.equipoId = equipoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(long ordenId) {
        this.ordenId = ordenId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
