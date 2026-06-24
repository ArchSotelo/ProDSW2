package com.cibertec.ERP_Product.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaCreditoDebitoRequest {
    private Integer idFactura;
    private String tipo;
    private String motivo;
    private Double monto;
}
