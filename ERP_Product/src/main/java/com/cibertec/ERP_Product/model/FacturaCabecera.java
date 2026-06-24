package com.cibertec.ERP_Product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_factura_cabecera")
public class FacturaCabecera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFactura;

    @Column(name = "numero_factura")
    private String numeroFactura;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @ManyToOne @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "cabecera", cascade = CascadeType.ALL)
    private List<FacturaDetalle> detalles;

    @Column(name = "estado")
    private String estado;

    @Column(name = "total")
    private Double total;

    @Column(name = "observaciones")
    private String observaciones;
}
