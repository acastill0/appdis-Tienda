package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Pelicula;
import modelo.Voto;

@Stateless
public class VotoDAO {
	@Inject
	private EntityManager em;

	/*public List<Voto> buscarVoto(String cedula, int idP) {
		String jpql = "SELECT p FROM Voto p WHERE p.idP=?0 AND p.usuario='?1'";
		// System.out.println(jpql);
		Query query = em.createQuery(jpql, Voto.class);
		query.setParameter(0, idP);
		query.setParameter(1, cedula);
		return query.getResultList();
	}*/

}
