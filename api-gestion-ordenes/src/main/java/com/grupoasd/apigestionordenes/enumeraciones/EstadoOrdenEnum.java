package com.grupoasd.apigestionordenes.enumeraciones;

public enum EstadoOrdenEnum {

    CREADA(0L, "Creada"), EN_PROCESO(1L, "En Proceso"), DESPACHADA(2L, "Despachada"),
    FINALIZADA(3L, "Finalizada");

    private Long id;
    private String nombre;

    private EstadoOrdenEnum(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static EstadoOrdenEnum getValueOf(Long id) {

        for (EstadoOrdenEnum estadoEquipo : EstadoOrdenEnum.values()) {
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
