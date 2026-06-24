package com.cibertec.ERP_Product.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenCompraRequest {
    private String numerador;
    private Integer idProveedor;
    private Integer idUsuario;
    private String observaciones;
    private List<OrdenCompraDetalleRequest> detalles;
}
