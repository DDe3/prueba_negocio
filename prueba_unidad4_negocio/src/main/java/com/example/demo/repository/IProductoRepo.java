package com.example.demo.repository;

import com.example.demo.repository.modelo.Producto;

public interface IProductoRepo {
	
	void insertarProducto(Producto producto);
	Producto buscarProducto(String codigo);
	void actualizarProducto(Producto producto);


}
