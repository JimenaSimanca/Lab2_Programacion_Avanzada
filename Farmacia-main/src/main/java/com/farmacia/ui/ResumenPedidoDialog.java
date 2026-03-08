package com.farmacia.ui;

import com.farmacia.model.Pedido;
import com.farmacia.service.PedidoService;
import com.farmacia.util.UIUtils;
import java.awt.*;
import java.util.stream.Collectors;
import javax.swing.*;

/**
 * Ventana de resumen y confirmación final del pedido. Se muestra tras validar
 * correctamente el formulario principal.
 *
 * Recibe un callback onPedidoEnviado que se ejecuta tras el envío exitoso para
 * limpiar el formulario de origen automáticamente.
 */
public class ResumenPedidoDialog extends JDialog {

    private final Pedido pedido;
    private final PedidoService pedidoService;
    private final Runnable onPedidoEnviado;

    public ResumenPedidoDialog(JFrame parent, Pedido pedido,
            PedidoService pedidoService,
            Runnable onPedidoEnviado) {
        super(parent, "Pedido al distribuidor " + pedido.getDistribuidor().getNombre(), true);
        this.pedido = pedido;
        this.pedidoService = pedidoService;
        this.onPedidoEnviado = onPedidoEnviado;
        inicializarUI();
        pack();
        setLocationRelativeTo(parent);
    }

    private void inicializarUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBackground(UIUtils.COLOR_FONDO);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        panelPrincipal.add(crearPanelTitulo(), BorderLayout.NORTH);
        panelPrincipal.add(crearPanelDetalle(), BorderLayout.CENTER);
        panelPrincipal.add(crearPanelBotones(), BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(UIUtils.COLOR_FONDO);
        JLabel titulo = new JLabel("Resumen del Pedido");
        titulo.setFont(UIUtils.FUENTE_TITULO);
        titulo.setForeground(UIUtils.COLOR_PRIMARIO);
        panel.add(titulo);
        return panel;
    }

    private JPanel crearPanelDetalle() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIUtils.COLOR_SECUNDARIO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIUtils.COLOR_BORDE),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        panel.add(crearEtiquetaDetalle("Medicamento:", pedido.getDescripcionMedicamento()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(crearEtiquetaDetalle("Distribuidor:", pedido.getDistribuidor().getNombre()));
        panel.add(Box.createVerticalStrut(10));
        panel.add(crearEtiquetaDetalle("Destino:", construirTextoDireccion()));

        return panel;
    }

    private JPanel crearEtiquetaDetalle(String etiqueta, String valor) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        fila.setBackground(UIUtils.COLOR_SECUNDARIO);

        JLabel lblEtiqueta = new JLabel(etiqueta + "  ");
        lblEtiqueta.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEtiqueta.setForeground(UIUtils.COLOR_TEXTO);

        JLabel lblValor = new JLabel("<html>" + valor + "</html>");
        lblValor.setFont(UIUtils.FUENTE_ETIQUETA);
        lblValor.setForeground(UIUtils.COLOR_TEXTO);

        fila.add(lblEtiqueta);
        fila.add(lblValor);
        return fila;
    }

    private String construirTextoDireccion() {
        return pedido.getSucursales().stream()
                .map(s -> "Para la farmacia " + s.getNombre()
                + " situada en " + s.getDireccion())
                .collect(Collectors.joining("<br>"));
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panel.setBackground(UIUtils.COLOR_FONDO);

        JButton btnCancelar = UIUtils.crearBotonSecundario("Cancelar");
        JButton btnEnviar = UIUtils.crearBotonPrimario("Enviar Pedido");

        btnCancelar.addActionListener(e -> dispose());
        btnEnviar.addActionListener(e -> confirmarEnvio());

        panel.add(btnCancelar);
        panel.add(btnEnviar);
        return panel;
    }

    private void confirmarEnvio() {
        pedidoService.enviarPedido(pedido);
        JOptionPane.showMessageDialog(this,
                "¡Pedido enviado correctamente al distribuidor "
                + pedido.getDistribuidor().getNombre() + "!",
                "Pedido Enviado",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
        onPedidoEnviado.run(); // limpia el formulario principal automáticamente
    }
}
