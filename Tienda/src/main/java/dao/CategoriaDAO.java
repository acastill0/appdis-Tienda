package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Categoria;
import modelo.Usuario;

@Stateless
public class CategoriaDAO {

	@Inject
	private EntityManager em;

	public void insertar(Categoria c) {
		em.persist(c);
	}

	public void actualizar(Categoria c) {
		em.merge(c);
	}

	public void borrar(int id) {
		em.remove(buscar(id));
	}

	public Categoria buscar(int id) {
		Categoria c;
		c = em.find(Categoria.class, id);
		return c;
	}

	public List<Categoria> ListadoPeliculas() {
		String jpql = "SELECT p FROM Pelicula p";
		Query q = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = q.getResultList();
		return categorias;
	}
}
