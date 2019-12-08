package dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Pelicula;
import modelo.Persona;

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

	public Persona buscar(int id) {
		Persona p;
		p=em.find(Persona.class, id);
		return p;
	}

}
