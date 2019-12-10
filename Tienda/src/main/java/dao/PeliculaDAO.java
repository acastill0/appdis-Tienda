package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Pelicula;
import modelo.Usuario;

@Stateless
public class PeliculaDAO {

	@Inject
	private EntityManager em;

	public void insertar(Pelicula p) {
		em.persist(p);
	}

	public void actualizar(Pelicula p) {
		em.merge(p);
	}

	public void borrar(int id) {
		em.remove(buscar(id));
	}

	public Usuario buscar(int id) {
		Usuario p;
		p = em.find(Usuario.class, id);
		return p;
	}

	public List<Pelicula> ListadoPersonas() {
		String jpql = "SELECT p FROM Pelicula p";
		Query q = em.createQuery(jpql, Pelicula.class);
		List<Pelicula> peliculas = q.getResultList();
		return peliculas;
	}

}
