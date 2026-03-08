package com.farmacia.model;

/**
 * Tipos de medicamento disponibles para pedido.
 */
public enum TipoMedicamento {
    ANALGESICO("Analgésico"),
    ANALEPTICO("Analéptico"),
    ANESTESICO("Anestésico"),
    ANTIACIDO("Antiácido"),
    ANTIDEPRESIVO("Antidepresivo"),
    ANTIBIOTICO("Antibiótico");

    private final String nombre;

    TipoMedicamento(String nombre) {
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
