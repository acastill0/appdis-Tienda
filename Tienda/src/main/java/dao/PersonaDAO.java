package dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import modelo.Persona;

@Stateless
public class PersonaDAO {

	@Inject
	private EntityManager em;

	public void insertar(Persona p) {
		em.persist(p);
	}

	public void actualizar(Persona p) {
		em.merge(p);
	}

	public void borrar(String cedula) {
		em.remove(buscar(cedula));
	}

	public Persona buscar(String cedula) {
		Persona p;
		p=em.find(Persona.class, cedula);
		return p;
	}
}