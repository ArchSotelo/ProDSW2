package com.cibertec.ERP_Product.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoRequest {
    private MovimientoCabeceraRequest cabecera;
    private List<MovimientoDetalleRequest> detalles;
}
