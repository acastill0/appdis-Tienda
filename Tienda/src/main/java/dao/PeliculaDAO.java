package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Pelicula;
import modelo.Producto;
import modelo.Tarjeta;

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

		for (Pelicula pelicula : peliculas) {
			pelicula.getCategoria().setPeliculas(new ArrayList<Pelicula>());

		}

		return peliculas;
	}

	public List<Pelicula> listaPeliculasPopulares() {
		String jpql = "SELECT t  from t Pelicula order by t.votacion";
		Query query = em.createQuery(jpql, Pelicula.class);
		return query.getResultList();
	}

	public List<Pelicula> listaPeliculasVendidas() {
		String jpql = "SELECT t  from t Pelicula order by t.vendidas";
		Query query = em.createQuery(jpql, Pelicula.class);
		return query.getResultList();
	}

	public List<Producto> ListadoProductos() {
		String jpql = "SELECT p FROM Pelicula p";
		Query q = em.createQuery(jpql, Pelicula.class);
		List<Pelicula> peliculas = q.getResultList();
		List<Producto> productos = new ArrayList<Producto>();

		for (Pelicula pe : peliculas) {
			Producto pr = new Producto();
			pr.setId(pe.getId());
			pr.setTitulo(pe.getTitulo());
			pr.setImagen(pe.getImagen());
			pr.setCategoria(pe.getCategoria().getNombre());
			pr.setCantidad(pe.getCantidad());
			pr.setPrecio(pe.getPrecio());
			pr.setVotacion(pe.getVotacion());
			productos.add(pr);

		}
		return productos;
	}

	public List<Pelicula> listadoPeliculaBuscado(int id) {
		Pelicula pb = buscar(id);
		List<Pelicula> peliculaB = new ArrayList<Pelicula>();
		peliculaB.add(pb);
		return peliculaB;
	}

	public List<Pelicula> buscarPelicula(String titulo) {
		String jpql = "SELECT p FROM Pelicula p WHERE p.titulo like ?1";
		System.out.println(jpql);
		Query query = em.createQuery(jpql, Pelicula.class);
		query.setParameter(1, titulo);
		return query.getResultList();
	}
	


}
