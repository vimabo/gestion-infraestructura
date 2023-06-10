package com.grupoasd.apigestionordenes.enumeraciones;

public enum EstadoEquipoEnum {

    BUENAS_CONDICIONES(0L, "Buenas Condiciones"), REGULAR(1L, "Regular"), EQUIPO_OCUPADO(2L, "Equipo Ocupado"),
    REPARAR(3L, "Se Debe Reparar");

    private Long id;
    private String nombre;

    private EstadoEquipoEnum(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static EstadoEquipoEnum getValueOf(Long id) {

        for (EstadoEquipoEnum estadoEquipo : EstadoEquipoEnum.values()) {
            if (estadoEquipo.getId().equals(id)) {
                return estadoEquipo;
            }
        }

        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
