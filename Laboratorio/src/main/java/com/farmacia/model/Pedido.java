package com.farmacia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private String nombreMedicamento;
    private String tipoMedicamento;
    private int cantidad;
    private String distribuidor;
    private boolean farmaciaPrincipal;
    private boolean farmaciaSecundaria;

    public String getResumenMedicamento() {
        return cantidad + " unidades del " + tipoMedicamento + " " + nombreMedicamento;
    }

    public String getDireccionEnvio() {
        String principal = "Calle de la Rosa n. 28";
        String secundaria = "Calle Alcazabilla n. 3";

        if (farmaciaPrincipal && farmaciaSecundaria) {
            return "Para la farmacia situada en " + principal
                    + " y para la situada en " + secundaria;
        } else if (farmaciaPrincipal) {
            return "Para la farmacia situada en " + principal;
        } else if (farmaciaSecundaria) {
            return "Para la farmacia situada en " + secundaria;
        }
        return "";
    }
}