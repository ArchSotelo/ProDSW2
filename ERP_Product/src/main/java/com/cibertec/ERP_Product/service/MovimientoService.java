package com.cibertec.ERP_Product.service;

import com.cibertec.ERP_Product.DTO.MovimientoCabeceraRequest;
import com.cibertec.ERP_Product.DTO.MovimientoDetalleRequest;
import com.cibertec.ERP_Product.DTO.MovimientoRequest;
import com.cibertec.ERP_Product.model.Almacen;
import com.cibertec.ERP_Product.model.Documento;
import com.cibertec.ERP_Product.model.MovimientoCabecera;
import com.cibertec.ERP_Product.model.MovimientoDetalle;
import com.cibertec.ERP_Product.model.PrecioProducto;
import com.cibertec.ERP_Product.model.Producto;
import com.cibertec.ERP_Product.model.Proveedor;
import com.cibertec.ERP_Product.model.Saldo;
import com.cibertec.ERP_Product.model.Usuario;
import com.cibertec.ERP_Product.repository.AlmacenRepository;
import com.cibertec.ERP_Product.repository.DocumentoRepository;
import com.cibertec.ERP_Product.repository.MovimientoCabeceraRepository;
import com.cibertec.ERP_Product.repository.MovimientoDetalleRepository;
import com.cibertec.ERP_Product.repository.PrecioProductoRepository;
import com.cibertec.ERP_Product.repository.ProductoRepository;
import com.cibertec.ERP_Product.repository.ProveedorRepository;
import com.cibertec.ERP_Product.repository.SaldoRepository;
import com.cibertec.ERP_Product.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoCabeceraRepository cabeceraRepository;

    @Autowired
    private MovimientoDetalleRepository detalleRepository;

    @Autowired
    private SaldoRepository saldoRepository;

    @Autowired
    private PrecioProductoRepository precioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private AlmacenRepository almacenRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    public List<MovimientoCabecera> listaMovimientoCabecera() {return cabeceraRepository.findAll();}

    @Transactional
    public void registrarMovimiento(MovimientoRequest request) {
        if (request == null || request.getCabecera() == null) {
            throw new RuntimeException("La cabecera del movimiento es obligatoria");
        }
        if (request.getDetalles() == null || request.getDetalles().isEmpty()) {
            throw new RuntimeException("El movimiento debe tener al menos un detalle");
        }

        MovimientoCabecera cabecera = construirCabecera(request.getCabecera());
        MovimientoCabecera cabeceraGuardada = cabeceraRepository.save(cabecera);

        for (MovimientoDetalleRequest detalleRequest : request.getDetalles()) {
            MovimientoDetalle detalle = construirDetalle(cabeceraGuardada, detalleRequest);
            detalleRepository.save(detalle);
            actualizarSaldo(cabeceraGuardada, detalle);
            registrarPrecio(detalle.getProducto(), detalle.getPrecioUnitario());
        }
    }

    private MovimientoCabecera construirCabecera(MovimientoCabeceraRequest request) {
        if (request.getCodigoMovimiento() == null || request.getCodigoMovimiento().isBlank()) {
            throw new RuntimeException("El codigo del movimiento es obligatorio");
        }
        if (request.getTipo() == null) {
            throw new RuntimeException("El tipo de movimiento es obligatorio");
        }
        if (request.getIdUsuario() == null) {
            throw new RuntimeException("El usuario es obligatorio");
        }

        MovimientoCabecera cabecera = new MovimientoCabecera();
        cabecera.setCodigoMovimiento(request.getCodigoMovimiento());
        cabecera.setTipo(request.getTipo());
        cabecera.setMotivo(request.getMotivo());
        cabecera.setFecha(LocalDateTime.now());

        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        cabecera.setUsuario(usuario);

        if (request.getIdProveedor() != null) {
            Proveedor proveedor = proveedorRepository.findById(request.getIdProveedor())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
            cabecera.setProveedor(proveedor);
        }

        if (request.getIdAlmacenOrigen() != null) {
            Almacen origen = almacenRepository.findById(request.getIdAlmacenOrigen())
                    .orElseThrow(() -> new RuntimeException("Almacen origen no encontrado"));
            cabecera.setAlmacenOrigen(origen);
        }

        if (request.getIdAlmacenDestino() != null) {
            Almacen destino = almacenRepository.findById(request.getIdAlmacenDestino())
                    .orElseThrow(() -> new RuntimeException("Almacen destino no encontrado"));
            cabecera.setAlmacenDestino(destino);
        }

        if (request.getIdDocumento() != null) {
            Documento documento = documentoRepository.findById(request.getIdDocumento())
                    .orElseThrow(() -> new RuntimeException("Documento no encontrado"));
            cabecera.setDocumento(documento);
        }

        return cabecera;
    }

    private MovimientoDetalle construirDetalle(MovimientoCabecera cabecera, MovimientoDetalleRequest request) {
        if (request.getIdProducto() == null) {
            throw new RuntimeException("El id del producto es obligatorio");
        }
        if (request.getCantidad() == null || request.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a cero");
        }
        if (request.getPrecioUnitario() == null || request.getPrecioUnitario() < 0) {
            throw new RuntimeException("El precio unitario debe ser mayor o igual a cero");
        }

        Producto producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        MovimientoDetalle detalle = new MovimientoDetalle();
        detalle.setCabecera(cabecera);
        detalle.setProducto(producto);
        detalle.setCantidad(request.getCantidad());
        detalle.setPrecioUnitario(request.getPrecioUnitario());
        detalle.setPrecioTotal(request.getCantidad() * request.getPrecioUnitario());
        return detalle;
    }

    private void actualizarSaldo(MovimientoCabecera cabecera, MovimientoDetalle detalle) {
        Producto producto = detalle.getProducto();
        Almacen almacenDestino = cabecera.getAlmacenDestino();
        Almacen almacenOrigen = cabecera.getAlmacenOrigen();

        if (cabecera.getTipo() == MovimientoCabecera.TipoMovimiento.ENTRADA) {
            if (almacenDestino == null) {
                throw new RuntimeException("El almacen destino es obligatorio para movimientos de entrada");
            }
            Saldo saldo = saldoRepository.findByProductoAndAlmacen(producto, almacenDestino)
                    .orElseGet(() -> crearSaldo(producto, almacenDestino, detalle.getPrecioUnitario()));
            saldo.setCantidad(saldo.getCantidad() + detalle.getCantidad());
            saldo.setPrecioUnitario(detalle.getPrecioUnitario());
            saldo.setPrecioTotal(saldo.getCantidad() * saldo.getPrecioUnitario());
            saldoRepository.save(saldo);
            return;
        }

        if (cabecera.getTipo() == MovimientoCabecera.TipoMovimiento.SALIDA) {
            if (almacenOrigen == null) {
                throw new RuntimeException("El almacen origen es obligatorio para movimientos de salida");
            }
            Saldo saldo = saldoRepository.findByProductoAndAlmacen(producto, almacenOrigen)
                    .orElseThrow(() -> new RuntimeException("No hay stock disponible"));
            if (saldo.getCantidad() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto " + producto.getIdProducto());
            }
            saldo.setCantidad(saldo.getCantidad() - detalle.getCantidad());
            saldo.setPrecioTotal(saldo.getCantidad() * saldo.getPrecioUnitario());
            saldoRepository.save(saldo);
            return;
        }

        if (almacenOrigen == null || almacenDestino == null) {
            throw new RuntimeException("La transferencia requiere almacen origen y destino");
        }

        Saldo saldoOrigen = saldoRepository.findByProductoAndAlmacen(producto, almacenOrigen)
                .orElseThrow(() -> new RuntimeException("No hay stock disponible en el almacen origen"));
        if (saldoOrigen.getCantidad() < detalle.getCantidad()) {
            throw new RuntimeException("Stock insuficiente para transferir el producto " + producto.getIdProducto());
        }
        saldoOrigen.setCantidad(saldoOrigen.getCantidad() - detalle.getCantidad());
        saldoOrigen.setPrecioTotal(saldoOrigen.getCantidad() * saldoOrigen.getPrecioUnitario());
        saldoRepository.save(saldoOrigen);

        Saldo saldoDestino = saldoRepository.findByProductoAndAlmacen(producto, almacenDestino)
                .orElseGet(() -> crearSaldo(producto, almacenDestino, detalle.getPrecioUnitario()));
        saldoDestino.setCantidad(saldoDestino.getCantidad() + detalle.getCantidad());
        saldoDestino.setPrecioUnitario(detalle.getPrecioUnitario());
        saldoDestino.setPrecioTotal(saldoDestino.getCantidad() * saldoDestino.getPrecioUnitario());
        saldoRepository.save(saldoDestino);
    }

    private Saldo crearSaldo(Producto producto, Almacen almacen, Double precioUnitario) {
        Saldo saldo = new Saldo();
        saldo.setProducto(producto);
        saldo.setAlmacen(almacen);
        saldo.setCantidad(0);
        saldo.setPrecioUnitario(precioUnitario);
        saldo.setPrecioTotal(0.0);
        return saldo;
    }

    private void registrarPrecio(Producto producto, Double precioUnitario) {
        PrecioProducto precio = new PrecioProducto();
        precio.setProducto(producto);
        precio.setFecha(LocalDate.now());
        precio.setPrecioUnitario(precioUnitario);
        precioRepository.save(precio);
    }
}
