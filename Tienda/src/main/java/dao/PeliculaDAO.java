package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Pelicula;

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

	public Pelicula buscar(int id) {
		Pelicula p;
		p = em.find(Pelicula.class, id);
		return p;
	}

	public List<Pelicula> ListadoPeliculas() {
		String jpql = "SELECT p FROM Pelicula p";
		Query q = em.createQuery(jpql, Pelicula.class);
		List<Pelicula> peliculas = q.getResultList();
		return peliculas;
	}

	public List<Pelicula> listadoPeliculaBuscado(int id) {
		Pelicula pb = buscar(id);
		List<Pelicula> peliculaB = new ArrayList<Pelicula>();
		peliculaB.add(pb);
		return peliculaB;
	}
}
