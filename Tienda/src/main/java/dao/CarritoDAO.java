package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.Carrito;

/*
 * Clase Crud para Carrito
 * @author: Lucy Garay, Adriana Castillo
 * */

@Stateless
public class CarritoDAO {
	@Inject
	private EntityManager em;
	/**
     * Método que actualiza al Carrito
     */
	public void actualizar(Carrito c) {
		em.merge(c);
	}
	/**
     * Método que devuelve la lista con el contenido del Carrito del cliente
     * @return Los detalles que forman parte del carrito del cliente
     */
	public List<Carrito> comprasCliente() {
		String jpql = "SELECT u FROM Carrito u WHERE u.estado = :p";
		Query q = em.createQuery(jpql, Carrito.class);
		q.setParameter("p", true);
		//q.setParameter("p", true);
		List<Carrito> carritos = q.getResultList();
		return carritos;	
	}
	
}
