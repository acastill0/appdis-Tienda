package dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Carrito;
import modelo.Compra;
import modelo.Detalle;
import modelo.Pelicula;
import modelo.Usuario;

public class CarritoDAO {
	@Inject
	private EntityManager em;


	public List<Carrito> comprasCliente() {
		String jpql = "SELECT u FROM Carrito u WHERE u.estado = :p";
		Query q = em.createQuery(jpql, Carrito.class);
		q.setParameter("p", true);
		//q.setParameter("p", true);
		List<Carrito> carritos = q.getResultList();
		return carritos;	
	}
	
}
