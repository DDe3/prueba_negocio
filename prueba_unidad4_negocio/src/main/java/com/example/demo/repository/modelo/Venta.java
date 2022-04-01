package com.example.demo.repository.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_venta")
	@SequenceGenerator(name = "seq_venta", sequenceName = "seq_venta", allocationSize = 1)
	@Column(name="vent_id")
	private Integer id;
	@Column(name="vent_numero")
	private String numero;
	@Column(name="vent_fecha")
	private LocalDate fecha;
	@Column(name="vent_cedula")
	private String cedula;
	@Column(name="vent_total_venta")
	private Double totalVenta;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "venta")
	private DetalleVenta detalleVenta;
	
	
	
	
	public DetalleVenta getDetalleVenta() {
		return detalleVenta;
	}
	public void setDetalleVenta(DetalleVenta detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Double getTotalVenta() {
		return totalVenta;
	}
	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}
	
	
	

}
