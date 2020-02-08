package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Pelicula;
import modelo.Producto;

/*
 * Clase Crud para Pelicula
 * @author: Lucy Garay, Adriana Castillo
 * */
@Stateless
public class PeliculaDAO {

	@Inject
	private EntityManager em;

	/**
	 * Método que crea una Pelicula
	 */
	public void insertar(Pelicula p) {
		em.persist(p);
	}

	/**
	 * Método que actualiza una Pelicula
	 */
	public void actualizar(Pelicula p) {
		em.merge(p);
	}

	/**
	 * Método que borra una Pelicula
	 */
	public void borrar(int id) {
		em.remove(buscar(id));
	}

	/**
	 * Método que busca una Película
	 * 
	 * @return La Película buscada
	 */
	public Pelicula buscar(int id) {
		Pelicula p;
		p = em.find(Pelicula.class, id);
		return p;
	}

	/**
	 * Método retorna una lista de Películas
	 * 
	 * @return Las películas existentes
	 */
	public List<Pelicula> ListadoPeliculas() {
		String jpql = "SELECT p FROM Pelicula p";
		Query q = em.createQuery(jpql, Pelicula.class);
		List<Pelicula> peliculas = q.getResultList();

		/*
		 * for (Pelicula pelicula : peliculas) {
		 * pelicula.getCategoria().setPeliculas(new ArrayList<Pelicula>()); }
		 */
		return peliculas;
	}

	/**
	 * Método retorna una lista de Películas más vendida
	 * 
	 * @return Las películas más vendidas
	 */
	public List<Pelicula> listaPeliculasVendidasTop() {
		String jpql = "SELECT p FROM Pelicula p ORDER BY p.vendidos DESC";
		Query query = em.createQuery(jpql, Pelicula.class);
		return query.getResultList();
	}

	/**
	 * Método retorna una lista de Películas más votadas
	 * 
	 * @return Las películas más vendidas
	 */
	public List<Pelicula> listaPeliculasVotadasTop() {
		String jpql = "SELECT p FROM Pelicula p ORDER BY p.votacion DESC";
		Query query = em.createQuery(jpql, Pelicula.class);
		return query.getResultList();
	}

	/**
	 * Método retorna una lista de Películas
	 * 
	 * @return Las películas existentes
	 */
	public List<Producto> ListadoProductos() {
		String jpql = "SELECT p FROM Pelicula p";
		Query q = em.createQuery(jpql, Pelicula.class);
		List<Pelicula> peliculas = q.getResultList();
		List<Producto> productos = new ArrayList<Producto>();

		for (Pelicula pe : peliculas) {
			Producto p = new Producto();
			p.setId(pe.getId());
			p.setTitulo(pe.getTitulo());
			p.setImagen(pe.getImagen());
			p.setCategoria(pe.getCategoria().getNombre());
			p.setCantidad(pe.getCantidad());
			p.setPrecio(pe.getPrecio());
			p.setVotacion(pe.getVotacion());
			productos.add(p);
		}
		return productos;
	}
	public List<Pelicula> listadoPeliculaBuscado(int id) {
		Pelicula pb = buscar(id);
		List<Pelicula> peliculaB = new ArrayList<Pelicula>();
		peliculaB.add(pb);
		return peliculaB;
	}
}
