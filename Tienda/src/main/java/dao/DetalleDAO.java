package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Detalle;
/*
 * Clase Crud para Detalle
 * @author: Lucy Garay, Adriana Castillo
 * */
@Stateless
public class DetalleDAO {
	@Inject
	private EntityManager entityManager;
	/**
     * Método que devuelve la lista de los Detalles de un Carrito
     * @return Los items del carrito
     */
	public List<Detalle> ListadoDetalles() {
		String jpql = "SELECT d FROM Detalle d";
		Query q = entityManager.createQuery(jpql, Detalle.class);
		List<Detalle> detalles = q.getResultList();
		return detalles;
	}
}
