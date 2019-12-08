package dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Categoria;
import modelo.Persona;

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

	public Persona buscar(int id) {
		Persona p;
		p=em.find(Persona.class, id);
		return p;
	}

}
