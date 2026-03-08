package com.farmacia.model;

import java.util.List;

/**
 * Representa un pedido de medicamentos de una farmacia a un distribuidor. Clase
 * inmutable tras su construcción para garantizar consistencia de datos.
 */
public class Pedido {

    private final String nombreMedicamento;
    private final TipoMedicamento tipoMedicamento;
    private final int cantidad;
    private final Distribuidor distribuidor;
    private final List<Sucursal> sucursales;

    public Pedido(String nombreMedicamento, TipoMedicamento tipoMedicamento,
            int cantidad, Distribuidor distribuidor, List<Sucursal> sucursales) {
        this.nombreMedicamento = nombreMedicamento;
        this.tipoMedicamento = tipoMedicamento;
        this.cantidad = cantidad;
        this.distribuidor = distribuidor;
        this.sucursales = List.copyOf(sucursales);
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public TipoMedicamento getTipoMedicamento() {
        return tipoMedicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public String getDescripcionMedicamento() {
        return cantidad + " unidades del " + tipoMedicamento.getNombre().toLowerCase()
                + " " + nombreMedicamento;
    }
}
