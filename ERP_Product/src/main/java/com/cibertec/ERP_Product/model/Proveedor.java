package com.cibertec.ERP_Product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "codigo_proveedor", nullable = false, unique = true, length = 50)
    private String codigoProveedor;

    @Column(name = "ruc",nullable = false, length = 11)
    private String ruc;

    @Column(name = "razon_social",nullable = false, length = 150)
    private String razonSocial;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "direccion",  length = 255)
    private String direccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoProveedor tipo;

    @Column(name = "estado" , nullable = false)
    private boolean estado = true;

    public enum TipoProveedor {
        NACIONAL,
        INTERNACIONAL
    }

}
