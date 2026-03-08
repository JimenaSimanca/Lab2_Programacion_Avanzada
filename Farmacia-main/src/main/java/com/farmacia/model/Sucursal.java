package com.farmacia.model;

/**
 * Sucursales de la farmacia que pueden recibir pedidos.
 */
public enum Sucursal {
    PRINCIPAL("Principal", "Calle de la Rosa n. 28"),
    SECUNDARIA("Secundaria", "Calle Alcazabilla n. 3");

    private final String nombre;
    private final String direccion;

    Sucursal(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
