package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.repository.modelo.Producto;
import com.example.demo.repository.modelo.Venta;

public interface IVentaRepo {
	
	void insertarVenta(Venta venta);
	List<Producto> productoPorFecha(LocalDate fecha);
	List<Producto> productoPorCategoria(String categoria);
	List<Producto> productoPorCantidad(Integer catidad);

}
