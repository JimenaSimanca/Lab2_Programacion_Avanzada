package com.farmacia.util;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Utilidades de interfaz gráfica reutilizables. Centraliza estilos y
 * configuraciones visuales del sistema.
 */
public final class UIUtils {

    public static final Color COLOR_PRIMARIO = new Color(0x1A73E8);
    public static final Color COLOR_SECUNDARIO = new Color(0xF1F3F4);
    public static final Color COLOR_ERROR = new Color(0xD32F2F);
    public static final Color COLOR_FONDO = new Color(0xFFFFFF);
    public static final Color COLOR_TEXTO = new Color(0x202124);
    public static final Color COLOR_BORDE = new Color(0xDADCE0);

    // Botón Confirmar — azul cielo brillante con borde azul oscuro
    private static final Color COLOR_BOTON_CONFIRMAR = new Color(0x4FC3F7);
    private static final Color COLOR_BOTON_CONFIRMAR_BORDE = new Color(0x0277BD);

    // Botón Borrar/Cancelar — ámbar claro con borde naranja oscuro
    private static final Color COLOR_BOTON_CANCELAR = new Color(0xFFCC80);
    private static final Color COLOR_BOTON_CANCELAR_BORDE = new Color(0xBF360C);

    // Texto de botones: azul muy oscuro (casi negro)
    private static final Color COLOR_BOTON_TEXTO = new Color(0x0A1929);

    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FUENTE_ETIQUETA = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_CAMPO = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 14);

    private UIUtils() {
        /* Utilidad estática — no instanciar */ }

    public static void configurarLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Continúa con el LookAndFeel por defecto si falla
        }
    }

    /**
     * Botón primario: Confirmar / Enviar — azul brillante, borde azul, texto
     * negro
     */
    public static JButton crearBotonPrimario(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_BOTON);
        boton.setBackground(COLOR_BOTON_CONFIRMAR);
        boton.setForeground(COLOR_BOTON_TEXTO);
        boton.setOpaque(true);
        boton.setFocusPainted(false);
        boton.setBorderPainted(true);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BOTON_CONFIRMAR_BORDE, 2),
                BorderFactory.createEmptyBorder(6, 18, 6, 18)
        ));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(145, 40));
        return boton;
    }

    /**
     * Botón secundario: Borrar / Cancelar — ámbar, borde naranja, texto negro
     */
    public static JButton crearBotonSecundario(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_BOTON);
        boton.setBackground(COLOR_BOTON_CANCELAR);
        boton.setForeground(COLOR_BOTON_TEXTO);
        boton.setOpaque(true);
        boton.setFocusPainted(false);
        boton.setBorderPainted(true);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BOTON_CANCELAR_BORDE, 2),
                BorderFactory.createEmptyBorder(6, 18, 6, 18)
        ));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(145, 40));
        return boton;
    }

    public static JTextField crearCampoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(FUENTE_CAMPO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        return campo;
    }

    public static Border crearBordeSección(String titulo) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_BORDE),
                titulo,
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                COLOR_PRIMARIO
        );
    }

    public static void mostrarErrores(Component parent, String errores) {
        JOptionPane.showMessageDialog(
                parent,
                errores,
                "Datos incompletos o incorrectos",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
