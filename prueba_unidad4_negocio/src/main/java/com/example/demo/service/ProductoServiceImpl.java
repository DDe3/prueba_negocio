package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IProductoRepo;
import com.example.demo.repository.modelo.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoRepo repo;

	@Override
	public String insertarProducto(Producto producto) {
		repo.insertarProducto(producto);
		return "Producto insertado con exito!";
	}
	
	

}
