package com.farmacia.ui;

import com.farmacia.model.Pedido;

import javax.swing.*;
import java.awt.*;

public class PedidoForm extends JFrame {

    private JTextField txtMedicamento;
    private JComboBox<String> cmbTipo;
    private JTextField txtCantidad;

    private JRadioButton rbCofarma;
    private JRadioButton rbEmpsephar;
    private JRadioButton rbCemefar;
    private ButtonGroup grupoDistribuidor;

    private JCheckBox chkPrincipal;
    private JCheckBox chkSecundaria;

    private JButton btnBorrar;
    private JButton btnConfirmar;

    public PedidoForm() {
        setTitle("Sistema de pedidos de farmacia");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Formulario de pedido");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));

        JPanel formulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        txtMedicamento = new JTextField(20);

        cmbTipo = new JComboBox<>(new String[]{
                "Seleccione un tipo",
                "Analgésico",
                "Analéptico",
                "Anestésico",
                "Antiácido",
                "Antidepresivo",
                "Antibióticos"
        });

        txtCantidad = new JTextField(20);

        rbCofarma = new JRadioButton("Cofarma");
        rbEmpsephar = new JRadioButton("Empsephar");
        rbCemefar = new JRadioButton("Cemefar");

        grupoDistribuidor = new ButtonGroup();
        grupoDistribuidor.add(rbCofarma);
        grupoDistribuidor.add(rbEmpsephar);
        grupoDistribuidor.add(rbCemefar);

        chkPrincipal = new JCheckBox("Farmacia Principal");
        chkSecundaria = new JCheckBox("Farmacia Secundaria");

        btnBorrar = new JButton("Borrar");
        btnConfirmar = new JButton("Confirmar");

        int y = 0;

        gbc.gridx = 0;
        gbc.gridy = y;
        formulario.add(new JLabel("Nombre del medicamento:"), gbc);
        gbc.gridx = 1;
        formulario.add(txtMedicamento, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        formulario.add(new JLabel("Tipo del medicamento:"), gbc);
        gbc.gridx = 1;
        formulario.add(cmbTipo, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        formulario.add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1;
        formulario.add(txtCantidad, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        formulario.add(new JLabel("Distribuidor:"), gbc);
        gbc.gridx = 1;
        JPanel panelRadios = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelRadios.add(rbCofarma);
        panelRadios.add(rbEmpsephar);
        panelRadios.add(rbCemefar);
        formulario.add(panelRadios, gbc);

        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        formulario.add(new JLabel("Sucursal:"), gbc);
        gbc.gridx = 1;
        JPanel panelChecks = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelChecks.add(chkPrincipal);
        panelChecks.add(chkSecundaria);
        formulario.add(panelChecks, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnBorrar);
        panelBotones.add(btnConfirmar);

        btnBorrar.addActionListener(e -> limpiarFormulario());
        btnConfirmar.addActionListener(e -> confirmarPedido());

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(formulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    public void limpiarFormulario() {
        txtMedicamento.setText("");
        cmbTipo.setSelectedIndex(0);
        txtCantidad.setText("");
        grupoDistribuidor.clearSelection();
        chkPrincipal.setSelected(false);
        chkSecundaria.setSelected(false);
        txtMedicamento.requestFocus();
    }

    private void confirmarPedido() {
        String nombre = txtMedicamento.getText().trim();
        String tipo = (String) cmbTipo.getSelectedItem();
        String cantidadTexto = txtCantidad.getText().trim();

        if (!nombre.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ ]+$")) {
            mostrarError("El nombre del medicamento debe contener caracteres alfanuméricos.");
            return;
        }

        if (cmbTipo.getSelectedIndex() == 0) {
            mostrarError("Debe seleccionar un tipo de medicamento.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad <= 0) {
                mostrarError("La cantidad debe ser un número entero positivo.");
                return;
            }
        } catch (NumberFormatException e) {
            mostrarError("La cantidad debe ser un número entero válido.");
            return;
        }

        String distribuidor = obtenerDistribuidorSeleccionado();
        if (distribuidor == null) {
            mostrarError("Debe seleccionar un distribuidor.");
            return;
        }

        boolean principal = chkPrincipal.isSelected();
        boolean secundaria = chkSecundaria.isSelected();

        if (!principal && !secundaria) {
            mostrarError("Debe seleccionar al menos una sucursal.");
            return;
        }

        Pedido pedido = new Pedido();
        pedido.setNombreMedicamento(nombre);
        pedido.setTipoMedicamento(tipo);
        pedido.setCantidad(cantidad);
        pedido.setDistribuidor(distribuidor);
        pedido.setFarmaciaPrincipal(principal);
        pedido.setFarmaciaSecundaria(secundaria);

        ResumenPedidoFrame resumen = new ResumenPedidoFrame(pedido, this);
        resumen.setVisible(true);
    }

    private String obtenerDistribuidorSeleccionado() {
        if (rbCofarma.isSelected()) return "Cofarma";
        if (rbEmpsephar.isSelected()) return "Empsephar";
        if (rbCemefar.isSelected()) return "Cemefar";
        return null;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error de validación",
                JOptionPane.ERROR_MESSAGE
        );
    }
}