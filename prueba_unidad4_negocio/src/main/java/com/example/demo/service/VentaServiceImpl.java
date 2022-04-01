package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IProductoRepo;
import com.example.demo.repository.IVentaRepo;
import com.example.demo.repository.modelo.DetalleVenta;
import com.example.demo.repository.modelo.Producto;
import com.example.demo.repository.modelo.Venta;
import com.example.demo.to.ProductosVender;

@Service
public class VentaServiceImpl implements IVentaService {
	
	private static final Double IVA = 0.12;
	
	@Autowired
	private IVentaRepo repo;
	
	@Autowired
	private IProductoRepo productoRepo;

	@Override
	public String realizarVentaIndividual(ProductosVender producto, String cedula) {
		
		Producto p = productoRepo.buscarProducto(producto.getProducto().getCodigoBarras());
		if (p.getStock()<producto.getCantidad()) {
			return "Stock insuficiente";
		} else {
			Venta v = new Venta();
			v.setNumero(UUID.randomUUID().toString());
			v.setFecha(LocalDate.now());
			v.setCedula(cedula);
			Double subtotal = dameTotal(producto);
			v.setTotalVenta(subtotal+ (subtotal*IVA));
			
			DetalleVenta detalle = new DetalleVenta();
			detalle.setCantidad(producto.getCantidad());
			detalle.setSubtotal(subtotal);
			detalle.addProducto(producto.getProducto());
			
			v.setDetalleVenta(detalle);
			detalle.setVenta(v);
			repo.insertarVenta(v);
			return "Venta exitosa";
		}
		
		
	}

	@Override
	public String realizarVentaMultiple(List<ProductosVender> producto, String cedula) {
		
		for (ProductosVender productosVender : producto) {
			Producto p = productoRepo.buscarProducto(productosVender.getProducto().getCodigoBarras());
			if (p.getStock()<productosVender.getCantidad()) {
				return "Stock insuficiente para producto " + p.getNombre();
			}
		}
		Venta v = new Venta();
		v.setNumero(UUID.randomUUID().toString());
		v.setFecha(LocalDate.now());
		v.setCedula(cedula);
		Double total = 0.0;
		for (ProductosVender productosVender : producto) {
			total += dameTotal(productosVender);
		}
		v.setTotalVenta(total+ (total*IVA));
		
		DetalleVenta detalle = new DetalleVenta();
		Integer cantidad = 0;
		for (ProductosVender productosVender : producto) {
			cantidad += productosVender.getCantidad();
		}
		detalle.setCantidad(cantidad);
		detalle.setSubtotal(total);
		for (ProductosVender productosVender : producto) {
			detalle.addProducto(productosVender.getProducto());
		}
		
		v.setDetalleVenta(detalle);
		detalle.setVenta(v);
		repo.insertarVenta(v);
		return "Venta exitosa";
		
	}
	
	private Double dameTotal(ProductosVender p) {
		Double subtotal = p.getCantidad()*p.getProducto().getPrecio();
		return subtotal;
	}

	@Override
	public List<Producto> productoPorFecha(String fecha) {
		return repo.productoPorFecha(LocalDate.parse(fecha));
	}

	@Override
	public List<Producto> productoPorCategoria(String categoria) {
		return repo.productoPorCategoria(categoria);
	}

	@Override
	public List<Producto> productoPorCantidad(Integer catidad) {
		return repo.productoPorCantidad(catidad);
	}



	
	
	

}
