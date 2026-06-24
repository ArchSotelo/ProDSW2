package com.cibertec.ERP_Product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_movimiento_cabecera")
public class MovimientoCabecera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cabecera")
    private Integer idCabecera;

    @Column(name = "codigo_movimiento", nullable = false, unique = true, length = 50)
    private String codigoMovimiento;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoMovimiento tipo; // ENTRADA, SALIDA, TRANSFERENCIA

    @ManyToOne
    @JoinColumn(name = "id_almacen_origen")
    private Almacen almacenOrigen;

    @ManyToOne
    @JoinColumn(name = "id_almacen_destino")
    private Almacen almacenDestino;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_documento")
    private Documento documento;

    @Column(name = "motivo", length = 255)
    private String motivo;


    public enum TipoMovimiento {
        ENTRADA,
        SALIDA,
        TRANSFERENCIA
    }

}
