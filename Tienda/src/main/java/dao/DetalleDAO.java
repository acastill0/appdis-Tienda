package dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Detalle;

public class DetalleDAO {
	@Inject
	private EntityManager entityManager;

	public List<Detalle> ListadoDetalles() {
		String jpql = "SELECT d FROM Detalle d";
		Query q = entityManager.createQuery(jpql, Detalle.class);
		List<Detalle> detalles = q.getResultList();
		return detalles;
	}
}
