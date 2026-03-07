package com.farmacia.ui;

import com.farmacia.model.Pedido;

import javax.swing.*;
import java.awt.*;

public class ResumenPedidoFrame extends JFrame {

    private final PedidoForm formulario;

    public ResumenPedidoFrame(Pedido pedido, PedidoForm formulario) {
        this.formulario = formulario;

        setTitle("Pedido al distribuidor " + pedido.getDistribuidor());
        setSize(520, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblMedicamento = new JLabel(pedido.getResumenMedicamento());
        JLabel lblDireccion = new JLabel("<html>" + pedido.getDireccionEnvio() + "</html>");

        lblMedicamento.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblDireccion.setFont(new Font("SansSerif", Font.PLAIN, 15));

        JPanel centro = new JPanel(new GridLayout(2, 1, 10, 10));
        centro.add(lblMedicamento);
        centro.add(lblDireccion);

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnEnviar = new JButton("Enviar pedido");

        btnCancelar.addActionListener(e -> dispose());

        btnEnviar.addActionListener(e -> {
            System.out.println("Pedido enviado");

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido enviado correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
            );

            formulario.limpiarFormulario();
            dispose();
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnCancelar);
        panelBotones.add(btnEnviar);

        panel.add(centro, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);
    }
}