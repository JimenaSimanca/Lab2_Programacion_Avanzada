package com.farmacia;

import com.farmacia.ui.PedidoForm;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println("No se pudo aplicar FlatLaf.");
        }

        SwingUtilities.invokeLater(() -> {
            PedidoForm form = new PedidoForm();
            form.setVisible(true);
        });
    }
}