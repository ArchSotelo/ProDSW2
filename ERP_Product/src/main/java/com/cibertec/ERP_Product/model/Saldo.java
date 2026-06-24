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
@Table(name = "tbl_saldo")
public class Saldo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_saldo")
    private Integer idSaldo;

    @ManyToOne @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne @JoinColumn(name = "id_almacen", nullable = false)
    private Almacen almacen;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "precio_total", nullable = false)
    private Double precioTotal;

    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion = LocalDateTime.now();

    public Saldo(Producto producto, Almacen almacenDestino, int i, Double precioUnitario) {
        this.producto = producto;
        this.almacen = almacenDestino;
        this.cantidad = i;
        this.precioUnitario = precioUnitario;
        this.precioTotal = i * precioUnitario;
        this.ultimaActualizacion = LocalDateTime.now();
    }
}
