package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.DTO.OrdenCompraDetalleRequest;
import com.cibertec.ERP_Product.DTO.OrdenCompraRequest;
import com.cibertec.ERP_Product.model.*;
import com.cibertec.ERP_Product.repository.OrdenCompraCabeceraRepository;
import com.cibertec.ERP_Product.repository.ProductoRepository;
import com.cibertec.ERP_Product.repository.ProveedorRepository;
import com.cibertec.ERP_Product.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenCompraService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private OrdenCompraCabeceraRepository ordenCompraCabeceraRepository;

    @Transactional
    public OrdenCompraCabecera registrarOrdenCompra(OrdenCompraRequest request) {
        Proveedor proveedor = proveedorRepository.findById(request.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        OrdenCompraCabecera cabecera = new OrdenCompraCabecera();
        cabecera.setNumerador(request.getNumerador());
        cabecera.setFecha(LocalDate.now());
        cabecera.setProveedor(proveedor);
        cabecera.setUsuario(usuario);
        cabecera.setObservaciones(request.getObservaciones());
        cabecera.setEstado("PENDIENTE");

        List<OrdenCompraDetalle> detalles = new ArrayList<>();
        for (OrdenCompraDetalleRequest d : request.getDetalles()) {
            Producto producto = productoRepository.findById(d.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            OrdenCompraDetalle detalle = new OrdenCompraDetalle();
            detalle.setCabecera(cabecera);
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(d.getPrecioUnitario());
            detalle.setPrecioTotal(d.getCantidad() * d.getPrecioUnitario());
            detalles.add(detalle);
        }
        cabecera.setDetalles(detalles);

        return ordenCompraCabeceraRepository.save(cabecera);
    }

    @Transactional
    public OrdenCompraCabecera aprobarOrden(Integer idOrden) {
        OrdenCompraCabecera orden = ordenCompraCabeceraRepository.findById(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        if (!orden.getEstado().equals("PENDIENTE")) {
            throw new RuntimeException("Solo se pueden aprobar órdenes pendientes");
        }
        orden.setEstado("APROBADA");
        return ordenCompraCabeceraRepository.save(orden);
    }

    @Transactional
    public OrdenCompraCabecera rechazarOrden(Integer idOrden, String motivo) {
        OrdenCompraCabecera orden = ordenCompraCabeceraRepository.findById(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        orden.setEstado("RECHAZADA");
        orden.setObservaciones(motivo);
        return ordenCompraCabeceraRepository.save(orden);
    }

    @Transactional
    public OrdenCompraCabecera cerrarOrden(Integer idOrden) {
        OrdenCompraCabecera orden = ordenCompraCabeceraRepository.findById(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        orden.setEstado("CERRADA");
        return ordenCompraCabeceraRepository.save(orden);
    }

    public List<OrdenCompraCabecera> listarPorEstado(String estado) {
        return ordenCompraCabeceraRepository.findByEstado(estado);
    }

}
