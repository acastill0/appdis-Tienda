package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Pelicula;
import modelo.Producto;

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

		/*for (Pelicula pelicula : peliculas) {
			pelicula.getCategoria().setPeliculas(new ArrayList<Pelicula>());
		}*/
		return peliculas;
	}

	public List<Producto> ListadoProductos() {
		String jpql = "SELECT p FROM Pelicula p";
		Query q = em.createQuery(jpql, Pelicula.class);
		List<Pelicula> peliculas = q.getResultList();
		List<Producto> productos = new ArrayList<Producto>();
		
		for (Pelicula pel : peliculas) {
			Producto pro= new Producto();
			pro.setId(pel.getId());
			pro.setTitulo(pel.getTitulo());
			pro.setImagen(pel.getImagen());
			pro.setCantidad(pel.getCantidad());
			pro.setCategoria(pel.getCategoria().getNombre());
			pro.setPrecio(pel.getPrecio());
			pro.setVotacion(pel.getVotacion());
			productos.add(pro);
		}

		/*for (Pelicula pelicula : peliculas) {
			pelicula.getCategoria().setPeliculas(new ArrayList<Pelicula>());
		}*/
		return productos;
	}
	public List<Pelicula> listadoPeliculaBuscado(int id) {
		Pelicula pb = buscar(id);
		List<Pelicula> peliculaB = new ArrayList<Pelicula>();
		peliculaB.add(pb);
		return peliculaB;
	}
}
