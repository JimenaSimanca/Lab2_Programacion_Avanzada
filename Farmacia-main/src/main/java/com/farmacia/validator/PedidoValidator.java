package com.farmacia.validator;

import com.farmacia.model.Distribuidor;
import com.farmacia.model.Sucursal;
import com.farmacia.model.TipoMedicamento;

import java.util.ArrayList;
import java.util.List;

/**
 * Valida los datos de un pedido antes de su confirmación. Responsabilidad
 * única: verificar integridad de datos de entrada.
 */
public class PedidoValidator {

    public ValidationResult validar(String nombreMedicamento, TipoMedicamento tipo,
            String cantidadTexto, Distribuidor distribuidor,
            List<Sucursal> sucursales) {
        List<String> errores = new ArrayList<>();

        validarNombreMedicamento(nombreMedicamento, errores);
        validarTipoMedicamento(tipo, errores);
        validarCantidad(cantidadTexto, errores);
        validarDistribuidor(distribuidor, errores);
        validarSucursales(sucursales, errores);

        return errores.isEmpty()
                ? ValidationResult.exitoso()
                : ValidationResult.conErrores(errores);
    }

    private void validarNombreMedicamento(String nombre, List<String> errores) {
        if (nombre == null || nombre.isBlank()) {
            errores.add("• El nombre del medicamento es obligatorio.");
        } else if (!nombre.matches(".*[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9].*")) {
            errores.add("• El nombre del medicamento debe contener caracteres alfanuméricos.");
        }
    }

    private void validarTipoMedicamento(TipoMedicamento tipo, List<String> errores) {
        if (tipo == null) {
            errores.add("• Debe seleccionar un tipo de medicamento.");
        }
    }

    private void validarCantidad(String cantidadTexto, List<String> errores) {
        if (cantidadTexto == null || cantidadTexto.isBlank()) {
            errores.add("• La cantidad es obligatoria.");
            return;
        }
        try {
            int cantidad = Integer.parseInt(cantidadTexto.trim());
            if (cantidad <= 0) {
                errores.add("• La cantidad debe ser un número entero positivo.");
            }
        } catch (NumberFormatException e) {
            errores.add("• La cantidad debe ser un número entero válido.");
        }
    }

    private void validarDistribuidor(Distribuidor distribuidor, List<String> errores) {
        if (distribuidor == null) {
            errores.add("• Debe seleccionar un distribuidor.");
        }
    }

    private void validarSucursales(List<Sucursal> sucursales, List<String> errores) {
        if (sucursales == null || sucursales.isEmpty()) {
            errores.add("• Debe seleccionar al menos una sucursal de destino.");
        }
    }
}
