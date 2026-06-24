package com.cibertec.ERP_Product.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false)
    private Integer idCategoria;

    @Column(name = "codigo_categoria",  nullable = false,unique = true,length = 10)
    private String codigoCategoria;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

}
