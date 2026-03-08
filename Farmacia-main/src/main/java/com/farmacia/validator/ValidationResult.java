package com.farmacia.validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Resultado de una validación. Encapsula errores encontrados. Sigue el patrón
 * Result Object para evitar excepciones como control de flujo.
 */
public class ValidationResult {

    private final List<String> errores;

    private ValidationResult(List<String> errores) {
        this.errores = Collections.unmodifiableList(errores);
    }

    public static ValidationResult exitoso() {
        return new ValidationResult(new ArrayList<>());
    }

    public static ValidationResult conErrores(List<String> errores) {
        return new ValidationResult(errores);
    }

    public boolean esValido() {
        return errores.isEmpty();
    }

    public List<String> getErrores() {
        return errores;
    }

    public String getMensajeErrores() {
        return String.join("\n", errores);
    }
}
