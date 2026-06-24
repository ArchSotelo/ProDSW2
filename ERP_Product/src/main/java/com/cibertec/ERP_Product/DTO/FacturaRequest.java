package com.cibertec.ERP_Product.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaRequest {
    private String numeroFactura;
    private Long idCliente;
    private Long idUsuario;
    private Double total;
    private List<FacturaDetalleRequest> detalles;
}
