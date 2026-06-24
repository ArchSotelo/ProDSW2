package com.cibertec.ERP_Product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @ManyToOne
    @JoinColumn(columnDefinition="INT UNSIGNED", name = "id_factura", nullable = false)
    private FacturaCabecera factura;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @Column(name = "estado")
    private String estado;
}
