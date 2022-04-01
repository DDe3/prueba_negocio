package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Producto;
import com.example.demo.service.IProductoService;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {
	
	@Autowired
	private IProductoService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertarProducto(@RequestBody Producto producto) {
		return ResponseEntity.ok(service.insertarProducto(producto));
	}

}
