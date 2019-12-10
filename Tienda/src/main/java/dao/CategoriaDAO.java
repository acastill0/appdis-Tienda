package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Categoria;
import modelo.Pelicula;

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

	public List<Categoria> listadoCategorias() {
		String jpql = "SELECT c FROM Categoria c";
		Query q = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = q.getResultList();
		return categorias;
	}

	public List<Categoria> listadoCategoriaBuscado(int id) {
		Categoria cb = buscar(id);
		List<Categoria> categoriaB = new ArrayList<Categoria>();
		categoriaB.add(cb);
		return categoriaB;
	}
}
