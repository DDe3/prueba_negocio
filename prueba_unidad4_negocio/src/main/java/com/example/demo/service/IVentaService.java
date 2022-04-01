package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.repository.modelo.Producto;
import com.example.demo.to.ProductosVender;

public interface IVentaService {
	
	String realizarVentaIndividual(ProductosVender producto, String cedula);
	String realizarVentaMultiple(List<ProductosVender> producto, String cedula);
	
	List<Producto> productoPorFecha(String fecha);
	List<Producto> productoPorCategoria(String categoria);
	List<Producto> productoPorCantidad(Integer catidad);

}
