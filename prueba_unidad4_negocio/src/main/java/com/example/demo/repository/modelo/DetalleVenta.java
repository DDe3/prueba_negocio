package com.example.demo.repository.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_detalle_venta")
	@SequenceGenerator(name = "seq_detalle_venta", sequenceName = "seq_detalle_venta", allocationSize = 1)
	@Column(name="deve_id")
	private Integer id;
	@Column(name="deve_id")
	private Integer cantidad;
	@Column(name="deve_subtotal")
	private Double subtotal;
	
	@JsonIgnore
	@OneToOne
	@MapsId
	private Venta venta;
	
	@OneToMany(mappedBy = "detalle")
	private List<Producto> productos;
	
	public void addProducto(Producto producto) {
		productos.add(producto);
	}
	
	public void removeProducto(Producto producto) {
		productos.remove(producto);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
	

}
