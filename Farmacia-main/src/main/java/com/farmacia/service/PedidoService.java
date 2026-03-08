package com.farmacia.service;

import com.farmacia.model.Pedido;

/**
 * Servicio responsable de procesar y enviar pedidos. Separa la lógica de
 * negocio de la capa de presentación.
 */
public class PedidoService {

    /**
     * Simula el envío de un pedido al distribuidor. En producción, aquí iría la
     * integración con sistemas externos.
     *
     * @param pedido el pedido confirmado a enviar
     */
    public void enviarPedido(Pedido pedido) {
        System.out.println("=== PEDIDO ENVIADO ===");
        System.out.println("Distribuidor : " + pedido.getDistribuidor().getNombre());
        System.out.println("Medicamento  : " + pedido.getDescripcionMedicamento());
        pedido.getSucursales().forEach(sucursal
                -> System.out.println("Sucursal     : " + sucursal.getNombre()
                        + " — " + sucursal.getDireccion())
        );
        System.out.println("=====================");
    }
}
