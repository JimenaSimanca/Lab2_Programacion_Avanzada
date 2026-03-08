package com.farmacia;

import com.farmacia.ui.MainWindow;
import com.farmacia.util.UIUtils;
import javax.swing.*;

/**
 * Punto de entrada de la aplicación. Lanza la interfaz gráfica en el Event
 * Dispatch Thread de Swing.
 */
public class Main {

    public static void main(String[] args) {
        UIUtils.configurarLookAndFeel();
        SwingUtilities.invokeLater(() -> {
            MainWindow ventana = new MainWindow();
            ventana.setVisible(true);
        });
    }
}
