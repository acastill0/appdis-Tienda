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

	/**
	 * Método que crea una Categoría
	 */
	public void insertar(Categoria c) {
		em.persist(c);
	}

	/**
	 * Método que actualiza una Categoría
	 */
	public void actualizar(Categoria c) {
		em.merge(c);
	}

	/**
	 * Método que borra una Categoría
	 */
	public void borrar(int id) {
		em.remove(buscar(id));
	}

	/**
	 * Método que busca una Categoría
	 * 
	 * @return La Categoría buscada
	 */
	public Categoria buscar(int id) {
		Categoria c;
		c = em.find(Categoria.class, id);
		return c;
	}
	/**
     * Método que devuelve la lista de las Categorias
     * @return Las Categorías existentes
     */
	public List<Categoria> listadoCategorias() {
		String jpql = "SELECT c FROM Categoria c";
		Query q = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = q.getResultList();
		return categorias;
	}
	
}
