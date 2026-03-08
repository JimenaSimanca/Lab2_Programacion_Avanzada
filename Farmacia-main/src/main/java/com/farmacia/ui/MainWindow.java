package com.farmacia.ui;

import com.farmacia.service.PedidoService;
import com.farmacia.util.UIUtils;
import com.farmacia.validator.PedidoValidator;

import javax.swing.*;

/**
 * Ventana principal de la aplicación. Responsabilidad: contener y mostrar el
 * panel del formulario de pedido.
 */
public class MainWindow extends JFrame {

    public MainWindow() {
        super("Farmacia — Sistema de Pedidos");
        inicializarVentana();
    }

    private void inicializarVentana() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        PedidoValidator validator = new PedidoValidator();
        PedidoService pedidoService = new PedidoService();

        FormularioPedidoPanel formulario = new FormularioPedidoPanel(this, validator, pedidoService);
        setContentPane(formulario);

        pack();
        setMinimumSize(getPreferredSize());
        setLocationRelativeTo(null); // centra en pantalla
    }
}
