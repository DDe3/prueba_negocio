package com.example.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Producto;
import com.example.demo.service.IVentaService;
import com.example.demo.to.ListaProductosVender;
import com.example.demo.to.ProductosVender;

@RestController
@RequestMapping("api/ventas")
public class VentaRestController {

	@Autowired
	private IVentaService service;

	@PostMapping(path = "/{cedula}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> realizarVenta(@RequestBody ProductosVender pv,
			@PathVariable("cedula") String cedula) {
		return ResponseEntity.ok(service.realizarVentaIndividual(pv, cedula));
	}

	@PostMapping(path = "/{cedula}/varios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> realizarVenta(@RequestBody ListaProductosVender pv,
			@PathVariable("cedula") String cedula) {
		return ResponseEntity.ok(service.realizarVentaMultiple(pv.getPvs(), cedula));
	}

	@GetMapping(path = "/reportes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> buscarPorFecha(@PathParam(value = "fecha") String fecha) {
		return ResponseEntity.ok(service.productoPorFecha(fecha));
	}

	@GetMapping(path = "/reportes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> buscarPorCategoria(@PathParam(value = "categoria") String categoria) {
		return ResponseEntity.ok(service.productoPorFecha(categoria));
	}

	@GetMapping(path = "/reportes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> buscarPorCantidad(@PathParam(value = "cantidad") Integer cantidad) {
		return ResponseEntity.ok(service.productoPorCantidad(cantidad));
	}

}
