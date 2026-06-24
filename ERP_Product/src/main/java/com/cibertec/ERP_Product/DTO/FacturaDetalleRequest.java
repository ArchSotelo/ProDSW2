package com.cibertec.ERP_Product.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDetalleRequest {
    private Integer idProducto;
    private Integer cantidad;
    private Double precioUnitario;
}
