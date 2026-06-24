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
@Table(name = "tbl_nota_credito_debito")
public class NotaCreditoDebito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNota;

    @ManyToOne
    @JoinColumn(columnDefinition="INT UNSIGNED", name = "id_factura", nullable = false)
    private FacturaCabecera factura;

    @Column(name = "tipo")
    private String tipo; // CREDITO o DEBITO

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "fecha")
    private LocalDate fecha;
}
