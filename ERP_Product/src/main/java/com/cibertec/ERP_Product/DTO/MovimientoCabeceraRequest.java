package com.cibertec.ERP_Product.DTO;

import com.cibertec.ERP_Product.model.MovimientoCabecera;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoCabeceraRequest {
    private String codigoMovimiento;
    private MovimientoCabecera.TipoMovimiento tipo;
    private String motivo;
    private Integer idUsuario;
    private Integer idProveedor;
    private Integer idAlmacenOrigen;
    private Integer idAlmacenDestino;
    private Integer idDocumento;
}
