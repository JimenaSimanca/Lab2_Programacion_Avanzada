package com.farmacia.ui;

import com.farmacia.model.*;
import com.farmacia.service.PedidoService;
import com.farmacia.util.UIUtils;
import com.farmacia.validator.PedidoValidator;
import com.farmacia.validator.ValidationResult;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel principal del formulario de pedido de medicamentos. Agrupa todos los
 * campos del formulario con sus respectivos controles Swing.
 */
public class FormularioPedidoPanel extends JPanel {

    private final JFrame parentFrame;
    private final PedidoValidator validator;
    private final PedidoService pedidoService;

    // Controles del formulario
    private JTextField campoNombreMedicamento;
    private JComboBox<TipoMedicamento> comboTipoMedicamento;
    private JTextField campoCantidad;
    private ButtonGroup grupoDistribuidor;
    private JRadioButton radioCofarma;
    private JRadioButton radioEmpsephar;
    private JRadioButton radioCemefar;
    private JCheckBox checkPrincipal;
    private JCheckBox checkSecundaria;

    public FormularioPedidoPanel(JFrame parentFrame, PedidoValidator validator,
            PedidoService pedidoService) {
        this.parentFrame = parentFrame;
        this.validator = validator;
        this.pedidoService = pedidoService;
        inicializarUI();
    }

    private void inicializarUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(UIUtils.COLOR_FONDO);
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        add(crearPanelTitulo(), BorderLayout.NORTH);
        add(crearPanelFormulario(), BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);
    }

    // ─── Sección de título ──────────────────────────────────────────────────
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(UIUtils.COLOR_FONDO);

        JLabel titulo = new JLabel("Sistema de Pedidos de Medicamentos");
        titulo.setFont(UIUtils.FUENTE_TITULO);
        titulo.setForeground(UIUtils.COLOR_PRIMARIO);
        panel.add(titulo);
        return panel;
    }

    // ─── Formulario central ─────────────────────────────────────────────────
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(UIUtils.COLOR_FONDO);
        GridBagConstraints gbc = crearGbc();

        agregarSeccionMedicamento(panel, gbc);
        agregarSeccionDistribuidor(panel, gbc);
        agregarSeccionSucursal(panel, gbc);

        return panel;
    }

    private void agregarSeccionMedicamento(JPanel panel, GridBagConstraints gbc) {
        JPanel seccion = new JPanel(new GridBagLayout());
        seccion.setBackground(UIUtils.COLOR_FONDO);
        seccion.setBorder(UIUtils.crearBordeSección("Datos del Medicamento"));

        GridBagConstraints g = crearGbc();

        // Nombre
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        seccion.add(crearEtiqueta("Nombre del medicamento:"), g);
        g.gridx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;
        campoNombreMedicamento = UIUtils.crearCampoTexto();
        seccion.add(campoNombreMedicamento, g);

        // Tipo
        g.gridx = 0;
        g.gridy = 1;
        g.weightx = 0;
        g.fill = GridBagConstraints.NONE;
        seccion.add(crearEtiqueta("Tipo de medicamento:"), g);
        g.gridx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;
        comboTipoMedicamento = new JComboBox<>(TipoMedicamento.values());
        comboTipoMedicamento.insertItemAt(null, 0);
        comboTipoMedicamento.setSelectedIndex(0);
        comboTipoMedicamento.setFont(UIUtils.FUENTE_CAMPO);
        seccion.add(comboTipoMedicamento, g);

        // Cantidad
        g.gridx = 0;
        g.gridy = 2;
        g.weightx = 0;
        g.fill = GridBagConstraints.NONE;
        seccion.add(crearEtiqueta("Cantidad requerida:"), g);
        g.gridx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;
        campoCantidad = UIUtils.crearCampoTexto();
        seccion.add(campoCantidad, g);

        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panel.add(seccion, gbc);
    }

    private void agregarSeccionDistribuidor(JPanel panel, GridBagConstraints gbc) {
        JPanel seccion = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        seccion.setBackground(UIUtils.COLOR_FONDO);
        seccion.setBorder(UIUtils.crearBordeSección("Distribuidor"));

        grupoDistribuidor = new ButtonGroup();
        radioCofarma = crearRadioButton(Distribuidor.COFARMA.getNombre());
        radioEmpsephar = crearRadioButton(Distribuidor.EMPSEPHAR.getNombre());
        radioCemefar = crearRadioButton(Distribuidor.CEMEFAR.getNombre());

        grupoDistribuidor.add(radioCofarma);
        grupoDistribuidor.add(radioEmpsephar);
        grupoDistribuidor.add(radioCemefar);

        seccion.add(radioCofarma);
        seccion.add(radioEmpsephar);
        seccion.add(radioCemefar);

        gbc.gridy = 1;
        panel.add(seccion, gbc);
    }

    private void agregarSeccionSucursal(JPanel panel, GridBagConstraints gbc) {
        JPanel seccion = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        seccion.setBackground(UIUtils.COLOR_FONDO);
        seccion.setBorder(UIUtils.crearBordeSección("Sucursal de Destino"));

        checkPrincipal = crearCheckBox(Sucursal.PRINCIPAL.getNombre()
                + " (" + Sucursal.PRINCIPAL.getDireccion() + ")");
        checkSecundaria = crearCheckBox(Sucursal.SECUNDARIA.getNombre()
                + " (" + Sucursal.SECUNDARIA.getDireccion() + ")");
        seccion.add(checkPrincipal);
        seccion.add(checkSecundaria);

        gbc.gridy = 2;
        panel.add(seccion, gbc);
    }

    // ─── Panel de botones ───────────────────────────────────────────────────
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel.setBackground(UIUtils.COLOR_FONDO);

        JButton btnBorrar = UIUtils.crearBotonSecundario("Borrar");
        JButton btnConfirmar = UIUtils.crearBotonPrimario("Confirmar");

        btnBorrar.addActionListener(e -> limpiarFormulario());
        btnConfirmar.addActionListener(e -> procesarConfirmacion());

        panel.add(btnBorrar);
        panel.add(btnConfirmar);
        return panel;
    }

    // ─── Lógica del formulario ──────────────────────────────────────────────
    private void procesarConfirmacion() {
        ValidationResult resultado = validator.validar(
                campoNombreMedicamento.getText().trim(),
                (TipoMedicamento) comboTipoMedicamento.getSelectedItem(),
                campoCantidad.getText().trim(),
                obtenerDistribuidorSeleccionado(),
                obtenerSucursalesSeleccionadas()
        );

        if (!resultado.esValido()) {
            UIUtils.mostrarErrores(this, resultado.getMensajeErrores());
            return;
        }

        Pedido pedido = construirPedido();
        ResumenPedidoDialog dialogo = new ResumenPedidoDialog(parentFrame, pedido, pedidoService, this::limpiarFormulario);
        dialogo.setVisible(true);
    }

    private Pedido construirPedido() {
        return new Pedido(
                campoNombreMedicamento.getText().trim(),
                (TipoMedicamento) comboTipoMedicamento.getSelectedItem(),
                Integer.parseInt(campoCantidad.getText().trim()),
                obtenerDistribuidorSeleccionado(),
                obtenerSucursalesSeleccionadas()
        );
    }

    private Distribuidor obtenerDistribuidorSeleccionado() {
        if (radioCofarma.isSelected()) {
            return Distribuidor.COFARMA;
        }
        if (radioEmpsephar.isSelected()) {
            return Distribuidor.EMPSEPHAR;
        }
        if (radioCemefar.isSelected()) {
            return Distribuidor.CEMEFAR;
        }
        return null;
    }

    private List<Sucursal> obtenerSucursalesSeleccionadas() {
        List<Sucursal> sucursales = new ArrayList<>();
        if (checkPrincipal.isSelected()) {
            sucursales.add(Sucursal.PRINCIPAL);
        }
        if (checkSecundaria.isSelected()) {
            sucursales.add(Sucursal.SECUNDARIA);
        }
        return sucursales;
    }

    private void limpiarFormulario() {
        campoNombreMedicamento.setText("");
        comboTipoMedicamento.setSelectedIndex(0);
        campoCantidad.setText("");
        grupoDistribuidor.clearSelection();
        checkPrincipal.setSelected(false);
        checkSecundaria.setSelected(false);
        campoNombreMedicamento.requestFocus();
    }

    // ─── Helpers de construcción de componentes ─────────────────────────────
    private GridBagConstraints crearGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        return gbc;
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(UIUtils.FUENTE_ETIQUETA);
        label.setForeground(UIUtils.COLOR_TEXTO);
        return label;
    }

    private JRadioButton crearRadioButton(String texto) {
        JRadioButton radio = new JRadioButton(texto);
        radio.setFont(UIUtils.FUENTE_ETIQUETA);
        radio.setBackground(UIUtils.COLOR_FONDO);
        return radio;
    }

    private JCheckBox crearCheckBox(String texto) {
        JCheckBox check = new JCheckBox(texto);
        check.setFont(UIUtils.FUENTE_ETIQUETA);
        check.setBackground(UIUtils.COLOR_FONDO);
        return check;
    }
}
