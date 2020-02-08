package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Detalle;
import modelo.Usuario;

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
	 * 
	 * @return Los items del carrito
	 */
	public List<Detalle> ListadoDetalles() {
		String jpql = "SELECT d FROM Detalle d";
		Query q = entityManager.createQuery(jpql, Detalle.class);
		List<Detalle> detalles = q.getResultList();
		return detalles;
	}

	/**
	 * Método que busca al Detalle
	 * 
	 * @return El detalle buscado
	 */
	public Detalle buscar(int id) {
		Detalle d;
		d = entityManager.find(Detalle.class, id);
		return d;
	}
	public void actualizar(Detalle p) {
		entityManager.merge(p);
	}
	/**
	 * Método que borra el Detalle
	 */
	public int borrar(int id) throws Exception {
        String sql = "DELETE FROM " + Detalle.class.getName() + " d where d.id= :c";
        System.out.println("Name "+Detalle.class.getName());
        Query query = entityManager.createQuery(sql).setParameter("c", id);
        int records = query.executeUpdate();
        return records;
    }
}
