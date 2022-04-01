package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Producto;

@Repository
@Transactional
public class ProductoRepoImpl implements IProductoRepo {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void insertarProducto(Producto producto) {
		Producto p = buscarProducto(producto.getCodigoBarras());
		if (p!=null) {
			p.setStock(p.getStock()+producto.getStock());
			actualizarProducto(p);
		} else {
			em.persist(producto);
		}
	}


	@Override
	public void actualizarProducto(Producto producto) {
		em.merge(producto);
	}

	@Override
	public Producto buscarProducto(String codigo) {
		TypedQuery<Producto> mq = em.createQuery("SELECT p FROM Producto WHERE p.codigoBarras=:codigo", Producto.class);
		mq.setParameter("codigo", codigo);
		return mq.getSingleResult();
	}
	
	

	
	
}
