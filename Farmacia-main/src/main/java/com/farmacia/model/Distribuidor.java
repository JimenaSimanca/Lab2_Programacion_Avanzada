package com.farmacia.model;

/**
 * Distribuidores farmacéuticos disponibles para realizar pedidos.
 */
public enum Distribuidor {
    COFARMA("Cofarma"),
    EMPSEPHAR("Empsephar"),
    CEMEFAR("Cemefar");

    private final String nombre;

    Distribuidor(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
