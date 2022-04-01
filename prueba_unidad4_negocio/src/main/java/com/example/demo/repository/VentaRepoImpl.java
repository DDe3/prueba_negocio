package com.example.demo.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.DetalleVenta;
import com.example.demo.repository.modelo.Producto;
import com.example.demo.repository.modelo.Venta;

@Repository
@Transactional
public class VentaRepoImpl implements IVentaRepo {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void insertarVenta(Venta venta) {
		em.persist(venta);
	}

	@Override
	public List<Producto> productoPorFecha(LocalDate fecha) {
		TypedQuery<Venta> mq = em.createQuery("SELECT v FROM Venta v WHERE v.fecha=:fecha",Venta.class);
		mq.setParameter("fecha", fecha);
		List<Venta> ventas = mq.getResultList();
		List<Producto> productos = new ArrayList<>();
		for (Venta venta : ventas) {
			List<Producto> pds = venta.getDetalleVenta().getProductos();
			pds.forEach(p -> productos.add(p));
		}
		return productos;
	}

	@Override
	public List<Producto> productoPorCategoria(String categoria) {
		TypedQuery<Venta> mq = em.createQuery("SELECT v FROM Venta v",Venta.class);
		List<Venta> ventas = mq.getResultList();
		List<Producto> productos = new ArrayList<>();
		for (Venta venta : ventas) {
			List<Producto> pds = venta.getDetalleVenta().getProductos();
			List<Producto> filtrado = pds.stream().filter(pt -> pt.getCategoria()==categoria).collect(Collectors.toList());
			filtrado.forEach(p -> productos.add(p));
		}
		return productos;
	}

	@Override
	public List<Producto> productoPorCantidad(Integer cantidad) {
		TypedQuery<Venta> mq = em.createQuery("SELECT v FROM Venta v",Venta.class);
		List<Venta> ventas = mq.getResultList();
		List<Producto> productos = new ArrayList<>();
		for (Venta venta : ventas) {
			DetalleVenta pds = venta.getDetalleVenta();
			if (pds.getCantidad()>cantidad) {
				List<Producto> prod = pds.getProductos();
				prod.forEach(p->productos.add(p));
			}
		}
		return productos;
	}
	
}
